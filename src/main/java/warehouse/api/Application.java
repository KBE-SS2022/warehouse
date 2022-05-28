package warehouse.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        //Use CSVImporter
        //Save each element of list in DB
        SpringApplication.run(Application.class, args);
        System.out.println("Greetings");
    }
}
