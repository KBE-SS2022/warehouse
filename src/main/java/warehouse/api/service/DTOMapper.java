package warehouse.api.service;

import org.springframework.stereotype.Component;
import warehouse.api.dto.IngredientDTO;
import warehouse.api.dto.PizzaDTO;
import warehouse.api.entity.Ingredient;
import warehouse.api.entity.Pizza;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DTOMapper {

    public IngredientDTO toIngredientDTO(Ingredient ingredient){
        Long id = ingredient.getId();
        String name = ingredient.getName();
        String brand = ingredient.getBrand();
        String countryOrigin = ingredient.getCountryOrigin();
        char nutritionScore = ingredient.getNutritionScore();
        Integer calories = ingredient.getCalories();
        Integer amount = ingredient.getAmount();
        Double weight = ingredient.getWeight();
        Double price = ingredient.getPrice();
        List<Long> pizzaIDs = ingredient.getPizzaIDs();

        return new IngredientDTO(id, name, brand, countryOrigin, nutritionScore, calories, amount, weight, price, pizzaIDs);
    }

    public PizzaDTO toPizzaDTO(Pizza pizza){
        Long id = pizza.getId();
        String name = pizza.getName();
        List<Long> ingredientIDs = pizza.getIngredients().stream()
                .map(Ingredient::getId).collect(Collectors.toList());

        return new PizzaDTO(id, name, ingredientIDs);
    }

    public Ingredient toIngredient(IngredientDTO ingredientDTO){
        Long id = ingredientDTO.getId();
        String name = ingredientDTO.getName();
        String brand = ingredientDTO.getBrand();
        String countryOrigin = ingredientDTO.getCountryOrigin();
        char nutritionScore = ingredientDTO.getNutritionScore();
        Integer calories = ingredientDTO.getCalories();
        Integer amount = ingredientDTO.getAmount();
        Double weight = ingredientDTO.getWeight();
        Double price = ingredientDTO.getPrice();
        List<Pizza> pizzas = new LinkedList<>();

        return new Ingredient(id, name, brand, countryOrigin, nutritionScore, calories, amount, weight, price, pizzas);
    }

    public Pizza toPizza(PizzaDTO pizzaDTO){
        Long id = pizzaDTO.getId();
        String name = pizzaDTO.getName();
        List<Ingredient> ingredients = new LinkedList<>();

        return new Pizza(id, name, ingredients);
    }
}
