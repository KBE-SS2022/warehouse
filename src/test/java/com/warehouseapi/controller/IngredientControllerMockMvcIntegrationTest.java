package com.warehouseapi.controller;
import warehouse.api.controller.IngredientController;
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
@WebMvcTest(IngredientController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class IngredientControllerMockMvcIntegrationTest {
    @Autowired
      private  MockMvc mockMvc;
        @MockBean
        private  IngredientService ingredientService;

        private Ingredient ingredient;

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
        this.mockMvc.perform(get("/ingredient/ingredients").contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", Matchers.is(2)))
                .andExpect(jsonPath("$[0].id").value("20"))
                .andExpect(jsonPath("$[0].calories").value("350"))
                .andExpect(jsonPath("$[0].weight").value("100.0"))
                .andExpect(jsonPath("$[1].name").value("Brot"));



    }
    @Test
    public void getIngredients_ShouldReturnEmptyList() throws Exception {
        List<Ingredient> emptyList=new ArrayList<>();
        when(ingredientService.getIngredients()).thenReturn(emptyList);
        this.mockMvc.perform(get("/ingredient/ingredients").contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", Matchers.is(0)));


    }

    @Test
    public void getIngredientsWithNotNeededParameterInput_ShouldReturnIngredientList()throws Exception{
        when(ingredientService.getIngredients()).thenReturn(List.of(this.ingredient));
        this.mockMvc.perform(get("/ingredient/ingredients","hi").contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", Matchers.is(1)))
                .andExpect(jsonPath("$[0].id").value("20"));
    }


     @Test
    public void getIngredientByID_ShouldGetIngredientById() throws Exception {
         when(ingredientService.getIngredient(20L)).thenReturn(this.ingredient);
        this.mockMvc.perform(get("/ingredient/ingredient/{id}",20L).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("20"))
                .andExpect(jsonPath("$.name").value("Salami"))
                .andExpect(jsonPath("$.brand").value("jaa"))
                .andExpect(jsonPath("$.countryOrigin").value("italy"))
                .andExpect(jsonPath("$.nutritionScore").value("d"))
                .andExpect(jsonPath("$.calories").value("350"))
                .andExpect(jsonPath("$.weight").value("100.0"))
                .andExpect(jsonPath("$.price").value("4.0"));





     }
    @Test
    public void getIngredientByID_ShouldGetIngredientNotFoundResponse() throws Exception {
        when(ingredientService.getIngredient(20L)).thenThrow(new IngredientNotFoundException("Ingredient with id :20 not found in Database"));
        this.mockMvc.perform(get("/ingredient/ingredient/{id}",20L).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());



    }
    @Test
    public void getIngredientByID_ShouldGetBadRequestResponse() throws Exception {
        when(ingredientService.getIngredient(20L)).thenThrow(new IngredientNotFoundException("Ingredient with id :20 not found in Database"));
        this.mockMvc.perform(get("/ingredient/ingredient/{id}","extraInputVariable").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());



    }


}