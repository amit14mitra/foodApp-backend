package com.example.FoodApp.exception;

public class FoodNotFoundException extends RuntimeException{

    public FoodNotFoundException() {
    }

    public FoodNotFoundException(String message) {
        super(message);
    }

    public FoodNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
