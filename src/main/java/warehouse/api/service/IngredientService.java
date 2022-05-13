package warehouse.api.service;

import org.springframework.stereotype.Service;
import warehouse.api.entity.Ingredient;
import warehouse.api.repository.IngredientDAO;

import java.util.List;

@Service
public class IngredientService {

    private final IngredientDAO ingredientDAO = new IngredientDAO();

    public List<Ingredient> getIngredients() {
        return IngredientDAO.getIngredients();
    }

    public Ingredient getIngredient(Long id) {
        return ingredientDAO.getIngredient(id);
    }
}
