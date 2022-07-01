package com.warehouseapi.controller;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import warehouse.api.Application;
import warehouse.api.controller.IngredientController;
import warehouse.api.exception.ControllerAdviceExceptionHandling;
import warehouse.api.exception.IngredientNotFoundException;
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

import warehouse.api.entity.Ingredient;
import warehouse.api.service.IngredientService;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes ={IngredientController.class, ControllerAdviceExceptionHandling.class})
@WebMvcTest(IngredientController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class IngredientControllerMockMvcIntegrationTest {
    @Autowired
    private  MockMvc mockMvc;
    @MockBean
    private  IngredientService ingredientService;

    private Ingredient ingredient;
    private String getIngredientListPath="/ingredients";
    private String getIngredientByIdPath="/ingredient/{id}";
    @BeforeAll
    void init () {
        this.ingredient=new Ingredient(20L,"Salami","jaa","italy",'d',350,1,100.0,4.0);
    }

    @Test
    void testMockMvcCreation(){
       assertNotNull(mockMvc);
    }
    @Test
    public void getIngredients_ShouldReturnIngredientList() throws Exception {
        Ingredient ingredient1=new Ingredient(30L,"Brot","noname","spain",'b',200,1,100.0,1.0);
        when(ingredientService.getIngredients()).thenReturn(List.of(this.ingredient,ingredient1));
        this.mockMvc.perform(get(this.getIngredientListPath).contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", Matchers.is(2)))
                .andExpect(jsonPath("$[0].id").value("20"))
                .andExpect(jsonPath("$[0].name").value("Salami"))
                .andExpect(jsonPath("$[0].brand").value("jaa"))
                .andExpect(jsonPath("$[0].countryOrigin").value("italy"))
                .andExpect(jsonPath("$[0].nutritionScore").value("d"))
                .andExpect(jsonPath("$[0].calories").value("350"))
                .andExpect(jsonPath("$[0].amount").value("1"))
                .andExpect(jsonPath("$[0].weight").value("100.0"))
                .andExpect(jsonPath("$[0].price").value("4.0"))

                .andExpect(jsonPath("$.size()", Matchers.is(2)))
                .andExpect(jsonPath("$[1].id").value("30"))
                .andExpect(jsonPath("$[1].name").value("Brot"))
                .andExpect(jsonPath("$[1].brand").value("noname"))
                .andExpect(jsonPath("$[1].countryOrigin").value("spain"))
                .andExpect(jsonPath("$[1].nutritionScore").value("b"))
                .andExpect(jsonPath("$[1].calories").value("200"))
                .andExpect(jsonPath("$[1].amount").value("1"))
                .andExpect(jsonPath("$[1].weight").value("100.0"))
                .andExpect(jsonPath("$[1].price").value("1.0"));




    }
    @Test
    public void getIngredients_ShouldReturnEmptyList() throws Exception {
        List<Ingredient> emptyList=new ArrayList<>();
        when(ingredientService.getIngredients()).thenReturn(emptyList);
        this.mockMvc.perform(get(this.getIngredientListPath).contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", Matchers.is(0)));


    }

    @Test
    public void getIngredientsWithNotNeededParameterInput_ShouldReturnIngredientList()throws Exception{
        Ingredient ingredient1=new Ingredient(30L,"Brot","noname","spain",'b',200,1,100.0,1.0);
        when(ingredientService.getIngredients()).thenReturn(List.of(this.ingredient,ingredient1));

        this.mockMvc.perform(get(this.getIngredientListPath,"hi").contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", Matchers.is(2)))
                .andExpect(jsonPath("$[0].id").value("20"))
                .andExpect(jsonPath("$[0].name").value("Salami"))
                .andExpect(jsonPath("$[0].brand").value("jaa"))
                .andExpect(jsonPath("$[0].countryOrigin").value("italy"))
                .andExpect(jsonPath("$[0].nutritionScore").value("d"))
                .andExpect(jsonPath("$[0].calories").value("350"))
                .andExpect(jsonPath("$[0].amount").value("1"))
                .andExpect(jsonPath("$[0].weight").value("100.0"))
                .andExpect(jsonPath("$[0].price").value("4.0"))

                .andExpect(jsonPath("$.size()", Matchers.is(2)))
                .andExpect(jsonPath("$[1].id").value("30"))
                .andExpect(jsonPath("$[1].name").value("Brot"))
                .andExpect(jsonPath("$[1].brand").value("noname"))
                .andExpect(jsonPath("$[1].countryOrigin").value("spain"))
                .andExpect(jsonPath("$[1].nutritionScore").value("b"))
                .andExpect(jsonPath("$[1].calories").value("200"))
                .andExpect(jsonPath("$[1].amount").value("1"))
                .andExpect(jsonPath("$[1].weight").value("100.0"))
                .andExpect(jsonPath("$[1].price").value("1.0"));
    }


     @Test
    public void getIngredientByID_ShouldGetIngredientById() throws Exception {
         when(ingredientService.getIngredient(20L)).thenReturn(this.ingredient);
        this.mockMvc.perform(get(this.getIngredientByIdPath,20L).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("20"))
                .andExpect(jsonPath("$.name").value("Salami"))
                .andExpect(jsonPath("$.brand").value("jaa"))
                .andExpect(jsonPath("$.countryOrigin").value("italy"))
                .andExpect(jsonPath("$.nutritionScore").value("d"))
                .andExpect(jsonPath("$.calories").value("350"))
                .andExpect(jsonPath("$.amount").value("1"))
                .andExpect(jsonPath("$.weight").value("100.0"))
                .andExpect(jsonPath("$.price").value("4.0"));





     }
    @Test
    public void getIngredientByID_ShouldGetIngredientNotFoundResponse() throws Exception {
        when(ingredientService.getIngredient(20L)).thenThrow(new IngredientNotFoundException("Ingredient with id :20 not found in Database"));
        this.mockMvc.perform(get(this.getIngredientByIdPath,20L).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());



    }
    @Test
    public void getIngredientByID_ShouldGetBadRequestResponse() throws Exception {
        when(ingredientService.getIngredient(20L)).thenThrow(new IngredientNotFoundException("Ingredient with id :20 not found in Database"));
        this.mockMvc.perform(get(this.getIngredientByIdPath,"extraInputVariable").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());



    }


}