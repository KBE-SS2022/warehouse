package warehouse.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import warehouse.api.entity.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    Ingredient save(Ingredient ingredient);
    void deleteById(Long id);
    Optional<Ingredient> findById(Long id);
    List<Ingredient> findAll();

}