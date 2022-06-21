package com.warehouseapi.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import warehouse.api.Application;
import warehouse.api.controller.PizzaController;
import warehouse.api.entity.Ingredient;
import warehouse.api.exception.ControllerAdviceExceptionHandling;
import warehouse.api.repository.IngredientRepository;
import warehouse.api.service.IngredientService;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@ContextConfiguration(classes ={IngredientRepository.class})
@DataJpaTest
public class IngredientEntityRepositoryTest {


    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private IngredientRepository ingredientRepository;

    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(ingredientRepository).isNotNull();
    }
    @Test
    public void saveIngredientTest(){
       // Ingredient ingredient=
    }

}
