package warehouse.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import warehouse.api.entity.Pizza;
import warehouse.api.service.PizzaService;


import java.util.List;

@RestController
@RequestMapping
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @GetMapping(path = "/pizzas", produces = "application/json")
    public List<Pizza> getPizzas() {
        return pizzaService.getPizzas();
    }


    @GetMapping(path = "/pizza/{id}", produces = "application/json")
    public Pizza getPizzaById(@PathVariable(value = "id") Long pizzaId) {
        return this.pizzaService.getPizza(pizzaId);
    }
}
