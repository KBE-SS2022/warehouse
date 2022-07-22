package warehouse.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import warehouse.api.entity.Ingredient;
import warehouse.api.entity.Pizza;
import warehouse.exception.IngredientNotFoundException;
import warehouse.api.repository.IngredientRepository;
import warehouse.dto.PizzaDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PizzaDTOMapper {

    @Autowired
    private IngredientRepository ingredientRepository;

    public PizzaDTO toPizzaDTO(Pizza pizza){
        Long id = pizza.getId();
        String name = pizza.getName();
        Map<Long,Double> ingredientIDs = pizza.getIngredients().stream()
                .collect(Collectors.toMap(Ingredient::getId, Ingredient::getPrice));

        return new PizzaDTO(id, name, ingredientIDs);
    }

    public Pizza toPizza(PizzaDTO pizzaDTO){
        Long pizza_id = pizzaDTO.getId();
        String name = pizzaDTO.getName();
        Map<Long,Double> ingredientIdToPrice = pizzaDTO.getIngredientIdToPrice();
        Set<Long> ingredientIDs = ingredientIdToPrice.keySet();

        List<Ingredient> ingredients = ingredientIDs.stream()
                .map(ingredient_id -> {
                    Optional<Ingredient> maybeIngredient = ingredientRepository.findById(ingredient_id);
                    if(maybeIngredient.isPresent()) return maybeIngredient.get();
                    else throw new IngredientNotFoundException(ingredient_id);
                })
                .collect(Collectors.toList());

        return new Pizza(pizza_id, name, ingredients);
    }
}