package com.example.FoodApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FoodExceptionHandler {

    @ExceptionHandler(value = {FoodNotFoundException.class})
    public ResponseEntity<Object> handleFoodException(FoodNotFoundException foodNotFoundException){
        FoodException foodException = new FoodException(
                foodNotFoundException.getMessage(),
                foodNotFoundException.getCause(),
                HttpStatus.NOT_FOUND
        );

        return new ResponseEntity<>(foodException, HttpStatus.NOT_FOUND);
    }
}
