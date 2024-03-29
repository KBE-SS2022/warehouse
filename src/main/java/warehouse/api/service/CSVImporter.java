package warehouse.api.service;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import com.opencsv.exceptions.CsvValidationException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import warehouse.api.entity.Ingredient;
import warehouse.api.entity.Pizza;
import warehouse.exception.CSVImportFailedException;
import warehouse.exception.IngredientNotFoundException;
import warehouse.util.HibernateUtil;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CSVImporter implements InitializingBean {

    private final static String PATH_INGREDIENTS = "ingredients.csv";
    private final static String PATH_PIZZAS = "pizzas.csv";

    private static final SessionFactory SESSION_FACTORY = HibernateUtil.getSessionFactory();

    @Override
    public void afterPropertiesSet() throws CSVImportFailedException {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();

        List<Ingredient> ingredients;
        List<Pizza> pizzas;

        try {
            ingredients = this.importIngredients();
            pizzas = this.importPizzas(ingredients);
        } catch (IOException e) {
            throw new CSVImportFailedException(e.getMessage());
        } catch (CsvValidationException e){
            throw new CSVImportFailedException("CSV file not valid");
        }
        ingredients = addPizzasToIngredients(ingredients, pizzas);

        ingredients.forEach(session::save);
        pizzas.forEach(session::save);

        session.flush();
        session.close();
    }

    private List<Ingredient> importIngredients() throws IOException, CsvValidationException {
        List<Ingredient> importedIngredients = new LinkedList<>();
        CSVReader reader = createCSVReader(PATH_INGREDIENTS);

        String[] line;
        while ( (line = reader.readNext() ) != null) {
            Ingredient newIngredient = new Ingredient(Long.parseLong(line[0]), line[1],
                    line[2], line[3], line[4].charAt(0), Integer.parseInt(line[5]), Integer.parseInt(line[6]),
                    Double.parseDouble(line[7]), Double.parseDouble(line[8]));
            importedIngredients.add(newIngredient);
        }
        reader.close();
        return importedIngredients;
    }

    private List<Pizza> importPizzas(List<Ingredient> ingredients) throws IOException, CsvValidationException {
        if(ingredients.isEmpty()) throw new NullPointerException("List of ingredients is empty");

        List<Pizza> importedPizzas = new LinkedList<>();
        CSVReader reader = createCSVReader(PATH_PIZZAS);

        String[] line;
        while ( (line = reader.readNext() ) != null) {
            List<Long> ingredient_ids = List.of( line[2].split(",") )
                    .stream()
                    .map(Long::parseLong)
                    .collect(Collectors.toList());

            List<Ingredient> ingredient_objects = ingredient_ids.stream()
                    .map(id -> {
                        List<Ingredient> ingredientList = ingredients.stream()
                                .filter(ingredient -> ingredient.getId().equals(id))
                                .collect(Collectors.toList());
                        if(ingredientList.isEmpty())
                            throw new IngredientNotFoundException(id);
                        return ingredientList.get(0);
                    })
                    .collect(Collectors.toList());

            Pizza newPizza = new Pizza(Long.parseLong(line[0]), line[1], ingredient_objects);
            importedPizzas.add(newPizza);
        }
        reader.close();
        return importedPizzas;
    }

    /*
     *  Method can only be called after Ingredients and Pizzas were successfully imported
     */
    private List<Ingredient> addPizzasToIngredients(List<Ingredient> ingredients, List<Pizza> pizzas) {
        if(ingredients.isEmpty()) throw new NullPointerException("List of ingredients is empty");
        if(pizzas.isEmpty()) throw new NullPointerException("List of pizzas is empty");

        return ingredients.stream()
                .map(ingredient -> {
                    List<Pizza> pizzasWithIngredient = pizzas.stream()
                            .filter(pizza -> pizza.getIngredients().contains(ingredient))
                            .collect(Collectors.toList());
                    ingredient.setPizzas(pizzasWithIngredient);
                    return ingredient;
                })
                .collect(Collectors.toList());
    }

    private CSVReader createCSVReader(String path) throws IOException {
        InputStream stream = getClass().getClassLoader().getResourceAsStream(path);
        if(stream == null) throw new IOException();
        Reader reader = new InputStreamReader(stream);

        CSVParser parser = new CSVParserBuilder().withSeparator(';')
                .withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_QUOTES).build();
        return new CSVReaderBuilder(reader).withSkipLines(1).withCSVParser(parser).build();
    }
}