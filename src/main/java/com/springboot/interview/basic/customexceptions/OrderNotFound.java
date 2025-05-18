package com.springboot.interview.basic.customexceptions;

public class OrderNotFound extends RuntimeException{

    public OrderNotFound(String msg){
        super(msg);
    }
}
