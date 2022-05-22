package warehouse.api.repository;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.stereotype.Repository;
import warehouse.api.entity.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.io.FileReader;


@Repository
public class IngredientDAO {

    private static final List<Ingredient> INGREDIENTS = new ArrayList<>();

    // Load CSV file before apps starts
    static {
        try(FileReader fr = new FileReader("src/main/resources/ingredients.csv")){
            CSVParser parser = new CSVParserBuilder().withSeparator(';')
                    .withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_QUOTES).build();
            CSVReader reader = new CSVReaderBuilder(fr).withSkipLines(1).withCSVParser(parser).build();

           String[] line;
           //possible FormatExceptions
           while ( (line = reader.readNext() ) != null) {
               INGREDIENTS.add( new Ingredient(Long.parseLong(line[0]), line[1],
                       line[2], line[3], line[4].charAt(0), Integer.parseInt(line[5]), Integer.parseInt(line[6]),
                       Double.parseDouble(line[7]), Double.parseDouble(line[8])) );
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

    public static List<Ingredient> getIngredients() {
        return INGREDIENTS;
    }

    public void addZutat(Ingredient ingredient) {
        INGREDIENTS.add(ingredient);
    }

    public Ingredient getIngredient(Long id) {
        return INGREDIENTS.stream()
                .filter( ingredient -> ingredient.getId().equals(id) )
                .collect(Collectors.toList()).get(0);
    }
}



