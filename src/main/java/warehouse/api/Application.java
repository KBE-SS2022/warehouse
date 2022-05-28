package warehouse.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import warehouse.api.service.CSVImporter;



@SpringBootApplication
public class Application {

    private static CSVImporter importer = new CSVImporter();

    public static void main(String[] args) {
        importer.saveRecordsToDB();
        SpringApplication.run(Application.class, args);
        System.out.println("Greetings");
    }
}
