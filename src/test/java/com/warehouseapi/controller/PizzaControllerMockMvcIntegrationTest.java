package com.warehouseapi.controller;


import warehouse.api.controller.PizzaController;
import warehouse.api.entity.Ingredient;
import warehouse.api.entity.Pizza;
import warehouse.api.exception.PizzaNotFoundException;
import warehouse.api.service.PizzaService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PizzaController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PizzaControllerMockMvcIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PizzaService pizzaService;

    private Pizza pizza;
    private String getPizzaListPath="/pizzas";
    private String getPizzaByIdPath="/pizza/{id}";
    @BeforeAll
    void init () {
        List<Ingredient>ingredientList=new LinkedList<>();
         ingredientList.add(new Ingredient(1011L,"Brot","jaa","spain",'b',150,1,100.0,2.5));
        this.pizza=new Pizza(10202L,"Salami",ingredientList);
    }



    @Test
    void testMockMvcCreation(){
        assertNotNull(mockMvc);
    }

    @Test

    public void getPizzas_ShouldReturnPizzaList() throws Exception {
        when(this.pizzaService.getPizzas()).thenReturn(List.of(this.pizza));
        this.mockMvc.perform(get(this.getPizzaListPath).contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", Matchers.is(1)))
                .andExpect(jsonPath("$.[0].size()", Matchers.is(3)))
                .andExpect(jsonPath("$[0].id").value("10202"))
                .andExpect(jsonPath("$[0].ingredients[0].name").value("Brot"))
                .andExpect(jsonPath("$[0].ingredients[0].calories").value(350))
                .andExpect(jsonPath("$[0].ingredients[0].weight").value(100.0))
                .andExpect(jsonPath("$[0].ingredients[1].name").value("Mozarella"))
                .andExpect(jsonPath("$[0].ingredients[2].name").value("Brot"));

    }
    @Test
    public void getPizzas_ShouldReturnEmptyList() throws Exception {
        List<Pizza> emptyList=new ArrayList<>();
        when(this.pizzaService.getPizzas()).thenReturn(emptyList);
        this.mockMvc.perform(get(this.getPizzaListPath).contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", Matchers.is(0)));


    }
    @Test
    public void getPizzasWithNotNeededParameterInput_ShouldReturnPizzaList()throws Exception{
        when(this.pizzaService.getPizzas()).thenReturn(List.of(this.pizza));
        this.mockMvc.perform(get(this.getPizzaListPath,"extraInputString",20).contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", Matchers.is(1)))
                .andExpect(jsonPath("$.[0].size()", Matchers.is(3)))
                .andExpect(jsonPath("$[0].id").value("10202"))
                .andExpect(jsonPath("$[0].ingredients[0].name").value("Salami"))
                .andExpect(jsonPath("$[0].ingredients[0].calories").value(350))
                .andExpect(jsonPath("$[0].ingredients[0].weight").value(100.0))
                .andExpect(jsonPath("$[0].ingredients[1].name").value("Mozarella"))
                .andExpect(jsonPath("$[0].ingredients[2].name").value("Brot"));

    }
    @Test
    public void getPizzaByID_ShouldGetPizzaByID() throws Exception {
        when(this.pizzaService.getPizza(10202L)).thenReturn(this.pizza);
        this.mockMvc.perform(get(this.getPizzaByIdPath,10202L).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ingredients.size()", Matchers.is(3)))
                .andExpect(jsonPath("$.id").value("10202"))
                .andExpect(jsonPath("$.ingredients[0].name").value("Salami"))
                .andExpect(jsonPath("$.ingredients[0].calories").value(350))
                .andExpect(jsonPath("$.ingredients[0].weight").value(100.0))
                .andExpect(jsonPath("$.ingredients[1].name").value("Mozarella"))
                .andExpect(jsonPath("$.ingredients[2].name").value("Brot"));
    }
    @Test
    public void getPizzaById_ShouldGetPizzatNotFoundResponse() throws Exception {
        when(this.pizzaService.getPizza(20L)).thenThrow(new PizzaNotFoundException("Pizza with id :20 not found in Database"));
        this.mockMvc.perform(get(this.getPizzaByIdPath,20L).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());



    }
    @Test
    public void getPizzaById_ShouldGetBadRequestResponse() throws Exception {
        this.mockMvc.perform(get(this.getPizzaByIdPath,"extraInputVariable").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());



    }


}
