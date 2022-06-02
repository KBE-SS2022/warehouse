package warehouse.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import warehouse.api.entity.Ingredient;
import warehouse.api.entity.Pizza;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {

    Pizza save(Ingredient ingredient);
    void deleteById(Long id);
    Optional<Pizza> findById(Long id);
    List<Pizza> findAll();

}