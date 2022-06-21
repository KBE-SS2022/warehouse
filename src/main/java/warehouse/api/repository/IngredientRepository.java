package warehouse.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import warehouse.api.entity.Ingredient;

import java.util.List;
import java.util.Optional;
@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    Ingredient save(Ingredient ingredient);
    Optional<Ingredient> findById(Long id);
    List<Ingredient> findAll();
    void delete(Ingredient ingredient);
}