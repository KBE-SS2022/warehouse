package warehouse.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import warehouse.api.dto.IngredientDTO;
import warehouse.api.entity.Ingredient;
import warehouse.api.service.DTOMapper;
import warehouse.api.service.IngredientService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class IngredientController {

	@Autowired
	private IngredientService ingredientService;
	@Autowired
	private DTOMapper mapper;

	@GetMapping(path = "/ingredients", produces = "application/json")
	public ResponseEntity<List<IngredientDTO>> getIngredients() {
		List<Ingredient> allIngredients = ingredientService.getIngredients();
		List<IngredientDTO> allIngredientsDTO = allIngredients.stream()
				.map(ingredient -> mapper.toIngredientDTO(ingredient)).collect(Collectors.toList());
		return new ResponseEntity<>(allIngredientsDTO, HttpStatus.OK);
	}

	/*
	@GetMapping(path = "/ingredients", produces = "application/json")
	public ResponseEntity<List<Ingredient>> getIngredients() {
		List<Ingredient> allIngredients = ingredientService.getIngredients();
		//List<IngredientDTO> allIngredientsDTO = allIngredients.stream()
				//.map(ingredient -> mapper.toIngredientDTO(ingredient)).collect(Collectors.toList());
		return new ResponseEntity<>(allIngredients, HttpStatus.OK);
	}
	*/

	@GetMapping(path = "/ingredient/{id}", produces = "application/json")
	public ResponseEntity<IngredientDTO> getIngredientById(@PathVariable(value = "id") Long ingredientId) {
		Ingredient ingredientById = this.ingredientService.getIngredient(ingredientId);
		IngredientDTO ingredientByIDDTO = mapper.toIngredientDTO(ingredientById);
		return new ResponseEntity<>(ingredientByIDDTO, HttpStatus.OK);
	}
}