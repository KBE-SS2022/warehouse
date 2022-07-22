package warehouse.api.service;

import warehouse.dto.IngredientDTO;
import warehouse.api.entity.Ingredient;
import warehouse.api.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import warehouse.exception.IngredientNotFoundException;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class IngredientService {

    @Autowired
    private IngredientDTOMapper dtoMapper;
    @Autowired
    private IngredientRepository ingredientRepository;

    public List<IngredientDTO> getIngredients() {
        List<Ingredient> allIngredients = ingredientRepository.findAll();
        return allIngredients.stream()
                .map(ingredient -> dtoMapper.toIngredientDTO(ingredient))
                .collect(Collectors.toList());
    }

    public IngredientDTO getIngredient(Long id) {
        Ingredient ingredient = ingredientRepository.findById(id).orElseThrow(()->
                new IngredientNotFoundException(id));
        return dtoMapper.toIngredientDTO(ingredient);
    }
}