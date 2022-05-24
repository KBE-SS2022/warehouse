package warehouse.api.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import warehouse.api.entity.Ingredient;

import javax.swing.text.html.parser.Entity;
import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

   //  Ingredient save(Ingredient ingredient);
    //int update(Ingredient ingredient);
    //int deleteById(Long id);
    //Ingredient findById(Long id);
    //List<Ingredient> findAll();

}