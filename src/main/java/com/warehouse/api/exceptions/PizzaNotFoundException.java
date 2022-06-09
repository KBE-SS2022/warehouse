package com.warehouse.api.exceptions;

public class PizzaNotFoundException extends Exception {


    public PizzaNotFoundException(String message){
        super(message);
    }
}
