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
	public List<Component> getComponents() {
		return componentService.getComponentsList();
	}


	@GetMapping(path = "/components/{id}", produces = "application/json")
	public List<Component> getEmployeeById(@PathVariable(value = "id") Long componentId) {
		return this.componentService.getComponent(componentId);
	}
}