package warehouse.api.repository;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import warehouse.api.entity.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.io.FileReader;

@Repository
public class PizzaDAO {

    private static final List<Pizza> PIZZAS = new ArrayList<>();

    @Autowired
    private IngredientRepository ingredientRepository;

    // Load CSV file before apps starts
    static {
        try(FileReader fr = new FileReader("src/main/resources/pizzas.csv")){
            CSVParser parser = new CSVParserBuilder().withSeparator(';')
                    .withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_QUOTES).build();
            CSVReader reader = new CSVReaderBuilder(fr).withSkipLines(1).withCSVParser(parser).build();

            String[] line;
            while ( (line = reader.readNext() ) != null) {
              /*  System.out.println("Line --- " + List.of( line[2].split(",") ).get(0));
                List<Long> idList = List.of( line[2].split(",") ).stream().map(Long::parseLong).collect(Collectors.toList());
                List<Ingredient> ing = idList.stream()
                        .map(ingredientRepository::findById)
                        .map(Optional::get)
                        .collect(Collectors.toList());*/

                PIZZAS.add( new Pizza(Long.parseLong(line[0]), line[1],
                        null) );
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            e.printStackTrace();
        }
    }

    public static List<Pizza> getPizzas() {
        return PIZZAS;
    }

    public void addPizza(Pizza pizza) {
        PIZZAS.add(pizza);
    }

    public Pizza getPizza(Long id) {
        return PIZZAS.stream()
                .filter( pizza -> pizza.getId().equals(id) )
                .collect(Collectors.toList()).get(0);
    }
}
