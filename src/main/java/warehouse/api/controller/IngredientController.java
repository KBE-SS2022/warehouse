package warehouse.api.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import warehouse.api.entity.Ingredient;
import warehouse.api.service.IngredientService;

import java.util.List;

@RestController
@RequestMapping
public class IngredientController {

	private final IngredientService ingredientService;

	@Autowired
	public IngredientController(IngredientService ingredientService){
		this.ingredientService = ingredientService;
	}

	@GetMapping(path = "/ingredients", produces = "application/json")
	public List<Ingredient> getIngredients() {
		return ingredientService.getIngredients();
	}


	@GetMapping(path = "/ingredient/{id}", produces = "application/json")
	public Ingredient getIngredientById(@PathVariable(value = "id") Long ingredientId) {
		return this.ingredientService.getIngredient(ingredientId);
	}
}