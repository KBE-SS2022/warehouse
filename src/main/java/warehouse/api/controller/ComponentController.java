package warehouse.api.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import warehouse.api.entity.Component;
import warehouse.api.service.ComponentService;

import java.util.List;

@RestController
@RequestMapping(path = "warehouse")
public class ComponentController {

	private final ComponentService componentService;

	@Autowired
	public ComponentController(ComponentService componentService){
		this.componentService = componentService;
	}

	@GetMapping(path = "/components", produces = "application/json")
	public ResponseEntity<List<Component>> getComponents() {
		List<Component> listAllComponents= componentService.getComponentsList();
		return new ResponseEntity<List<Component>>(listAllComponents, HttpStatus.OK);
	}
	@GetMapping(path = "/components/{id}", produces = "application/json")
	public ResponseEntity<List<Component>> getEmployeeById(@PathVariable(value = "id") int componentId) {
		List<Component> getEmployeeById=componentService.getComponentsList();
		return new ResponseEntity<List<Component>>(getEmployeeById, HttpStatus.OK);
	}
}
