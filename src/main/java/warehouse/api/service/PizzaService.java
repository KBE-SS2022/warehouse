package warehouse.api.service;

import warehouse.api.dto.PizzaDTO;
import warehouse.api.entity.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import warehouse.api.exception.PizzaNotFoundException;
import warehouse.api.repository.PizzaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PizzaService {

    @Autowired
    DTOMapper dtoMapper;
    @Autowired
    private PizzaRepository pizzaRepository;

    public List<PizzaDTO> getPizzas() {
        List<Pizza> allPizzas = pizzaRepository.findAll();
        return allPizzas.stream()
                .map(pizza -> dtoMapper.toPizzaDTO(pizza))
                .collect(Collectors.toList());
    }

    public PizzaDTO getPizza(Long id) throws PizzaNotFoundException {
        Pizza pizza =  pizzaRepository.findById(id).orElseThrow(()->
                new PizzaNotFoundException("Pizza with id: " + id + " not found in Database"));
        return dtoMapper.toPizzaDTO(pizza);
    }
}
