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
// Class to create a list of employees
public class ComponentDAO {

    private static final ArrayList<Component> componentsList = new ArrayList<>();
    private static Long id = 1L;

    // Load CSV file before apps starts
    static {
        try(FileReader fr = new FileReader("src/main/resources/components.csv")){
           CSVReader reader = new CSVReaderBuilder(fr).withSkipLines(1).build();

           String[] line;
           while ( (line = reader.readNext() ) != null) {
               componentsList.add( new Component(id, line[0], "b","b") );
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

    public static ArrayList<Component> getComponentsList() {
        return componentsList;
    }

    public void addComponent(Component component) {
        componentsList.add(component);
    }

    public List<Component> getComponent(Long id) {
        return componentsList.stream()
                .filter( component -> component.getId().equals(id) )
                .collect( Collectors.toList() );    }
}



