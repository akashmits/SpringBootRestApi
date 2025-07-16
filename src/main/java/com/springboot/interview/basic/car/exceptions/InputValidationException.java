package com.springboot.interview.basic.car.exceptions;

public class InputValidationException extends RuntimeException {

    public InputValidationException(String msg){
        super(msg);
    }
}
