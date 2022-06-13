package warehouse.api.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import warehouse.api.entity.Ingredient;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

   // List< Ingredient>saveAll(List<Ingredient> ingredients);
    Ingredient save(Ingredient ingredient);
    Optional<Ingredient> findById(Long id);
    List<Ingredient> findAll();
    void delete(Ingredient ingredient);


}