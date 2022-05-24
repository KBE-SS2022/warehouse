package warehouse.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import warehouse.api.entity.Pizza;
import warehouse.api.repository.PizzaDAO;

import java.util.List;

@Service
public class PizzaService {

    @Autowired
    private PizzaDAO pizzaDAO;

    public List<Pizza> getPizzas() {
        return PizzaDAO.getPizzas();
    }

    public Pizza getPizza(Long id) {
        return pizzaDAO.getPizza(id);
    }
}
