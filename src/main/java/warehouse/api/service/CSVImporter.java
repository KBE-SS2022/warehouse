package warehouse.api.service;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import warehouse.api.entity.Ingredient;
import warehouse.api.entity.Pizza;
import warehouse.api.exception.CSVImportFailedException;
import warehouse.api.repository.IngredientRepository;
import warehouse.api.repository.PizzaRepository;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CSVImporter implements InitializingBean {

    private final static String PATH_INGREDIENTS = "src/main/resources/ingredients.csv";
    private final static String PATH_PIZZAS = "src/main/resources/pizzas.csv";

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private PizzaRepository pizzaRepository;

    @Override
    public void afterPropertiesSet() throws CSVImportFailedException {
        List<Ingredient> ingredients = new LinkedList<>();
        List<Pizza> pizzas = new LinkedList<>();
        try {
            ingredients = this.importIngredients();
            pizzas = this.importPizzas();
        } catch (IOException e) {
            throw new CSVImportFailedException("Failed to read CSV file");
        } catch (CsvValidationException e){
            throw new CSVImportFailedException("CSV file not valid");
        }
        ingredients.forEach(ingredientRepository::save);
        pizzas.forEach(pizzaRepository::save);
    }

    private List<Ingredient> importIngredients() throws IOException, CsvValidationException{
        List<Ingredient> importedIngredients = new LinkedList<>();

        FileReader fr = new FileReader(PATH_INGREDIENTS);
        CSVParser parser = new CSVParserBuilder().withSeparator(';')
                .withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_QUOTES).build();
        CSVReader reader = new CSVReaderBuilder(fr).withSkipLines(1).withCSVParser(parser).build();

        String[] line;
        while ( (line = reader.readNext() ) != null) {
            importedIngredients.add( new Ingredient(Long.parseLong(line[0]), line[1],
                    line[2], line[3], line[4].charAt(0), Integer.parseInt(line[5]), Integer.parseInt(line[6]),
                    Double.parseDouble(line[7]), Double.parseDouble(line[8])) );
        }
        reader.close();
        return importedIngredients;
    }

    private List<Pizza> importPizzas() throws IOException, CsvValidationException{
        List<Pizza> importedPizzas = new LinkedList<>();

        FileReader fr = new FileReader(PATH_PIZZAS);
        CSVParser parser = new CSVParserBuilder().withSeparator(';')
                .withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_QUOTES).build();
        CSVReader reader = new CSVReaderBuilder(fr).withSkipLines(1).withCSVParser(parser).build();

        String[] line;
        while ( (line = reader.readNext() ) != null) {
            System.out.println("Line --- " + List.of( line[2].split(",") ).get(0));
            List<Long> idList = List.of( line[2].split(",") )
                    .stream()
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
            List<Ingredient> ing = idList.stream()
                    .map(ingredientRepository::findById)
                    .map(Optional::get)
                    .collect(Collectors.toList());

            importedPizzas.add( new Pizza(Long.parseLong(line[0]), line[1], ing) );
        }
        reader.close();
        return importedPizzas;
    }


}
