package warehouse.api.repository;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
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

    private static final ArrayList<Ingredient> INGREDIENTS = new ArrayList<>();
    private static Long id = 1L;

    // Load CSV file before apps starts
    static {
        try(FileReader fr = new FileReader("src/main/resources/components.csv")){
           CSVReader reader = new CSVReaderBuilder(fr).withSkipLines(1).build();

           String[] line;
           while ( (line = reader.readNext() ) != null) {
               INGREDIENTS.add( new Ingredient(id, line[0], "b", "b", 'A', 1, 2, 3.0) );
               id++;
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



