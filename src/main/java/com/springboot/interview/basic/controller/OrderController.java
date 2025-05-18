package com.springboot.interview.basic.controller;

import com.springboot.interview.basic.customexceptions.OrderNotFound;
import com.springboot.interview.basic.entities.Order;
import com.springboot.interview.basic.pojo.OrderPojo;
import com.springboot.interview.basic.service.OrderedService;
import com.springboot.interview.basic.iservice.IOrderedService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path="/v1/order")
public class OrderController {

    private IOrderedService orderedService;

    public OrderController(OrderedService orderedService){
        this.orderedService=orderedService;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody(required = true) OrderPojo orderPojo){
        System.out.println("Order Request Received :"+orderPojo);
        Order order =orderedService.order(orderPojo);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Order> getOrder(@RequestParam(required = true) Long orderId){
        Optional<Order> order = orderedService.order(orderId);
        if(order.isPresent())
        return new ResponseEntity<>(order.get(), HttpStatus.OK);
        else
            throw new OrderNotFound("Order Not found");
    }

}
