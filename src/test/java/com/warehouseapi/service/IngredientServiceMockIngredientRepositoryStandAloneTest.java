package com.warehouseapi.service;

import warehouse.api.repository.IngredientRepository;
import warehouse.api.service.IngredientService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import warehouse.api.entity.Ingredient;
import warehouse.api.exception.IngredientNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class IngredientServiceMockIngredientRepositoryStandAloneTest {
	@Mock
	private IngredientRepository mockedIngredientRepository;
	@InjectMocks
	private IngredientService ingredientService;
	private Ingredient ingredient;

	@BeforeEach
	public void init() {
		this.ingredient = new Ingredient(20L, "Salami", "jaa", "italy", 'd', 350, 1, 100.0, 4.0);
	}


	@Test
	void getIngredientById_GoodCase() throws IngredientNotFoundException {
		when(mockedIngredientRepository.findById(20L)).thenReturn(Optional.ofNullable((ingredient)));
		Assert.assertEquals((ingredient.getId()), ingredientService.getIngredient(20L).getId());


	}

	@Test
	void getIngredientById_NotFoundException() throws IngredientNotFoundException {
		when(mockedIngredientRepository.findById(20L)).thenReturn(Optional.ofNullable((null)));
		IngredientNotFoundException thrown = Assert.assertThrows(IngredientNotFoundException.class, () -> {
			ingredientService.getIngredient(20L);
		});
		Assert.assertEquals("Ingredient with id :20 not found in Database", thrown.getMessage());


	}

	@Test
	void getIngredients_ShouldReturnIngredientList() throws IngredientNotFoundException {
		Ingredient ingredient1 = new Ingredient(30L, "Brot", "noname", "spain", 'b', 200, 1, 100.0, 1.0);
		List<Ingredient> ingredientList = new ArrayList(Arrays.asList(ingredient1, this.ingredient));
		when(mockedIngredientRepository.findAll()).thenReturn(ingredientList);
		Assert.assertEquals("salami", ingredientService.getIngredients().get(0).getName());
		Assert.assertEquals("Brot", ingredientService.getIngredients().get(1).getName());

	}

	@Test
	void getIngredients_ShouldReturnEmptyIngredientList() throws IngredientNotFoundException {
		List<Ingredient>emptyIngredientList=new ArrayList<>();
		when(mockedIngredientRepository.findAll()).thenReturn(emptyIngredientList);
		Assert.assertEquals(0,ingredientService.getIngredients().size());
	}

}