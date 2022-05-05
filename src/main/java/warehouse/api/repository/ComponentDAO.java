package warehouse.api.repository;
import org.springframework.stereotype.Repository;
import warehouse.api.entity.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.io.FileReader;



@Repository

// Class to create a list
// of employees
public class ComponentDAO {

    private static ArrayList<Component> componentsList=new ArrayList<Component>();
    private static int id=1;




    static {

        String file = "/Users/aron/Desktop/KBE/demo/components.csv";
        List<String[]> content = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                componentsList.add(
                        new Component(id, line, "bla", "bla")
                );
                id++;
                content.add(line.split(","));
               System.out.println(line);

            }
        } catch (IOException e) {
            System.out.println("error");

        }


    }
    public   static ArrayList<Component> getComponentsList() {
        return componentsList;
    }

    public  void
    addComponent(Component component) {
        componentsList
                .add(component);

    }

    public  List<Component>  getComponent(int id) {

        List<Component> result = componentsList.stream().filter(component-> component.getId().equals(id))
                .collect(Collectors.toList());
        return  result;
    }
}



