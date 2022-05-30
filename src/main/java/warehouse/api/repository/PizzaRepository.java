package warehouse.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import warehouse.api.entity.Ingredient;
import warehouse.api.entity.Pizza;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {

    List< Pizza>saveAll(List<Pizza> pizzas);
    Pizza save(Pizza pizza);
    Optional<Pizza> findById(Long id);
    List<Pizza> findAll();
    void delete(Pizza pizza);



}