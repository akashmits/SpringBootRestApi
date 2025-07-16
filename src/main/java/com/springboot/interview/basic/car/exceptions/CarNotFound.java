package com.springboot.interview.basic.car.exceptions;

public class CarNotFound extends  RuntimeException{

    public CarNotFound(String msg){
        super(msg);
    }
}
