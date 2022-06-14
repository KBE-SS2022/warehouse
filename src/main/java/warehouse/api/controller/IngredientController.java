package warehouse.api.controller;

import
		org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import warehouse.api.entity.Ingredient;
import warehouse.api.exception.IngredientNotFoundException;
import warehouse.api.service.IngredientService;

import java.util.List;


@RestController
@RequestMapping(value="ingredient")
public class IngredientController {

	@Autowired
	private IngredientService ingredientService;

	@GetMapping(path = "/ingredients", produces = "application/json")
	public ResponseEntity<List<Ingredient>> getIngredients() {
		List<Ingredient> allIngredients = ingredientService.getIngredients();
		return new ResponseEntity<>(allIngredients, HttpStatus.OK);
	}

	@GetMapping(path = "/ingredient/{id}", produces = "application/json")
	public ResponseEntity<Ingredient> getIngredientById(@PathVariable(value = "id") Long ingredientId) {
		Ingredient ingredientById = this.ingredientService.getIngredient(ingredientId);
		return new ResponseEntity<>(ingredientById, HttpStatus.OK);
	}
}