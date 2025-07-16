package com.springboot.interview.basic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/v1/discount")
public class DiscountController {


    @GetMapping(path = "/total")
    public ResponseEntity<String>  getTotalDiscount(){
        return  ResponseEntity.ok("");
    }

    @PostMapping(path = "/settlement")
    public ResponseEntity<String>  discountSettlement(){

        return  ResponseEntity.ok("");
    }
}
