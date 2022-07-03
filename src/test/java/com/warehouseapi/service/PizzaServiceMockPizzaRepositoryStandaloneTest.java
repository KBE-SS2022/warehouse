package com.warehouseapi.service;

import org.junit.jupiter.api.Assertions;
import warehouse.api.entity.Ingredient;
import warehouse.api.entity.Pizza;
import warehouse.api.service.PizzaService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import warehouse.api.repository.PizzaRepository;
import warehouse.api.exception.IngredientNotFoundException;
import warehouse.api.exception.PizzaNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
class PizzaServiceMockPizzaRepositoryStandaloneTest {
    @Mock
    PizzaRepository pizzaRepository;

    private Pizza pizza;
    @InjectMocks
    private PizzaService pizzaService;

    @BeforeEach
    public void init(){
        List<Ingredient> ingredientList=new ArrayList<>();
        ingredientList.add(new Ingredient(20L,"Salami","jaa","italy",'d',350,1,100.0,4.0));
        ingredientList.add(new Ingredient(101L,"Mozarella","jaa","germany",'c',200,1,200.0,2.5));
        ingredientList.add(new Ingredient(1011L,"Brot","jaa","spain",'b',150,1,100.0,2.5));

        this.pizza= new Pizza(10111L,"Salami",ingredientList);


    }


   /* @Test
    void getPizzaById_ExpectGoodCase() throws  PizzaNotFoundException {
        when(pizzaRepository.findById(10111L)).thenReturn(Optional.ofNullable((this.pizza) ));
        Assert.assertEquals((pizza.getId()),pizzaService.getPizza(10111L).getId());
        Assert.assertEquals("Salami",(pizzaService.getPizza(10111L).getIngredients().get(0).getName()));
        Assert.assertEquals("jaa",(pizzaService.getPizza(10111L).getIngredients().get(0).getBrand()));
        Assert.assertEquals("italy",(pizzaService.getPizza(10111L).getIngredients().get(0).getCountryOrigin()));
        Assert.assertEquals('d',(pizzaService.getPizza(10111L).getIngredients().get(0).getNutritionScore()));
        Assert.assertEquals(350,(int)((pizzaService.getPizza(10111L).getIngredients().get(0).getCalories())));
        Assert.assertEquals(1,(int)(pizzaService.getPizza(10111L).getIngredients().get(0).getAmount()));
        Assertions.assertEquals(100.0, (double)(pizzaService.getPizza(10111L).getIngredients().get(0).getWeight()));
        Assertions.assertEquals(4.0, (double)(pizzaService.getPizza(10111L).getIngredients().get(0).getPrice()));

        Assert.assertEquals("Mozarella",(pizza.getIngredients()).get(1).getName());
        Assert.assertEquals("Brot",(pizza.getIngredients()).get(2).getName());




    }
    @Test
    void getPizzaByIdNotFoundException() throws IngredientNotFoundException {
        when(pizzaRepository.findById(20L)).thenReturn(Optional.ofNullable((null)));
        PizzaNotFoundException thrown = Assert.assertThrows(PizzaNotFoundException.class,() ->{
            pizzaService.getPizza(20L);
        });
        Assert.assertEquals("Pizza with id:20not found in Database", thrown.getMessage());



    }
    @Test
    void getPizzas_ShouldReturnPizzaList(){
        List<Ingredient> ingredientList2=new ArrayList<>();
        ingredientList2.add(new Ingredient(20L,"Thunfisch","jaa","italy",'d',350,1,100.0,4.0));
        ingredientList2.add(new Ingredient(101L,"Mozarella","jaa","germany",'c',200,1,200.0,2.5));
        ingredientList2.add(new Ingredient(1011L,"Brot","jaa","spain",'b',150,1,100.0,2.5));
        Pizza pizza2=new Pizza(3001L,"Thunfisch",ingredientList2);
        List<Pizza> pizzaList = new ArrayList(Arrays.asList(this.pizza, pizza2));
        when(pizzaRepository.findAll()).thenReturn(pizzaList);
        Assert.assertEquals("Salami", pizzaService.getPizzas().get(0).getName());
        Assert.assertEquals("Thunfisch", pizzaService.getPizzas().get(1).getName());
        Assert.assertEquals("Salami", pizzaService.getPizzas().get(0).getIngredients().get(0).getName());
        Assert.assertEquals("Thunfisch", pizzaService.getPizzas().get(1).getIngredients().get(0).getName());
    }*/
    @Test
    void getPizzas_ShouldReturnEmptyPizzaList(){
        List<Pizza>emptyPizzaList=new ArrayList<>();
        when(pizzaRepository.findAll()).thenReturn(emptyPizzaList);
        Assert.assertEquals(0,pizzaService.getPizzas().size());
    }


}
