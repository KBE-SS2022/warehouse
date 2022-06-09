package com.warehouse.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ControllerAdviceExceptionHandling extends ResponseEntityExceptionHandler {

   @ExceptionHandler(IngredientNotFoundException.class)
    public ResponseEntity<String> handleIngredientNotFoundException(IngredientNotFoundException ingredientNotFoundException){
        return new ResponseEntity<String>   (ingredientNotFoundException.getMessage(),HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(PizzaNotFoundException.class)
    public ResponseEntity<String> handlePizzaNotFoundException(PizzaNotFoundException pizzaNotFoundException){
        return new ResponseEntity<String>   (pizzaNotFoundException.getMessage(),HttpStatus.NOT_FOUND);
    }




}
