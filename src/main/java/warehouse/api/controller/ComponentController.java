package warehouse.api.controller;
import org.springframework.web.bind.annotation.PathVariable;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import warehouse.api.entity.Component;
import warehouse.api.repository.ComponentDAO;

import java.util.List;

@SpringBootApplication
@RestController
public class ComponentController {

	public static void main(String[] args) {
		SpringApplication.run(ComponentController.class, args);
		System.out.println("Greetings");
	}

	private ComponentDAO componentDAO = new ComponentDAO();

	@GetMapping(
			path = "/components",
			produces = "application/json")

	public List<Component> getComponents() {
		return componentDAO.getComponentsList();
	}


	@GetMapping(
			path = "/components/{id}",
			produces = "application/json")

	public List<Component> getEmployeeById(@PathVariable(value = "id") int componentId) {
		return this.componentDAO.getComponent(componentId);
	}
}