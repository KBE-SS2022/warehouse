package com.warehouse.api.service;

import com.warehouse.api.entity.Ingredient;
import com.warehouse.api.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.warehouse.api.exceptions.IngredientNotFoundException;

import java.util.List;


@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;


    public List<Ingredient> getIngredients() {
        return ingredientRepository.findAll();
    }

    public Ingredient getIngredient(Long id) throws IngredientNotFoundException {
        return ingredientRepository.findById(id).orElseThrow(()-> new IngredientNotFoundException("Ingredient with id :"+id+" not found in Database"));
    }
}
