package com.springboot.interview.basic.customexceptions;

public class CustomerNotFound extends RuntimeException{

    public CustomerNotFound(String msg){
        super(msg);
    }
}
