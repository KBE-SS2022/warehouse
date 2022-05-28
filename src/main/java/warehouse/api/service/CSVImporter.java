package warehouse.api.service;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import com.opencsv.exceptions.CsvValidationException;
import warehouse.api.entity.Ingredient;
import warehouse.api.entity.Pizza;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CSVImporter {

    private final static String PATH_INGREDIENTS = "src/main/resources/ingredients.csv";
    private final static String PATH_PIZZAS = "src/main/resources/pizzas.csv";


    public List<Ingredient> importIngredients() throws IOException, CsvValidationException{
        List<Ingredient> importedIngredients = new LinkedList<>();

        FileReader fr = new FileReader(PATH_INGREDIENTS);
        CSVParser parser = new CSVParserBuilder().withSeparator(';')
                .withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_QUOTES).build();
        CSVReader reader = new CSVReaderBuilder(fr).withSkipLines(1).withCSVParser(parser).build();

        String[] line;
        //possible FormatExceptions
        while ( (line = reader.readNext() ) != null) {
            importedIngredients.add( new Ingredient(Long.parseLong(line[0]), line[1],
                    line[2], line[3], line[4].charAt(0), Integer.parseInt(line[5]), Integer.parseInt(line[6]),
                    Double.parseDouble(line[7]), Double.parseDouble(line[8])) );
        }
        reader.close();
        return importedIngredients;
    }

    public List<Pizza> importPizzas() throws IOException, CsvValidationException{
        List<Pizza> importedPizzas = new LinkedList<>();

        FileReader fr = new FileReader(PATH_PIZZAS);
        CSVParser parser = new CSVParserBuilder().withSeparator(';')
                .withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_QUOTES).build();
        CSVReader reader = new CSVReaderBuilder(fr).withSkipLines(1).withCSVParser(parser).build();

        String[] line;
        //possible FormatExceptions
        while ( (line = reader.readNext() ) != null) {
            importedPizzas.add( new Pizza(Long.parseLong(line[0]), line[1],
                    new ArrayList<>()) );
        }
        reader.close();
        return importedPizzas;
    }
}
