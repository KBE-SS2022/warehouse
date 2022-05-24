package warehouse.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Pizza>> getPizzas() {
        List<Pizza> allPizzas = pizzaService.getPizzas();
        return new ResponseEntity<>(allPizzas,HttpStatus.OK);
    }


    @GetMapping(path = "/pizza/{id}", produces = "application/json")
    public ResponseEntity<Pizza> getPizzaById(@PathVariable(value = "id") Long pizzaId) {
        Pizza pizzaById = this.pizzaService.getPizza(pizzaId);
        return new ResponseEntity<>(pizzaById, HttpStatus.OK);
    }
}
