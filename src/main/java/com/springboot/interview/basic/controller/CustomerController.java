package com.springboot.interview.basic.controller;

import com.springboot.interview.basic.customexceptions.CustomerNotFound;
import com.springboot.interview.basic.entities.Customer;
import com.springboot.interview.basic.pojo.CustomerPojo;
import com.springboot.interview.basic.iservice.ICustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/v1/customer")
public class CustomerController {
    ICustomerService customerService;
    public CustomerController(ICustomerService customerService){
        this.customerService=customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> customer(@RequestBody  CustomerPojo customerPojo){
            System.out.println("Request Received :"+customerPojo);
            Customer customer = customerService.customer(customerPojo);
            return ResponseEntity.ok(customer);
    }

    @GetMapping
    public ResponseEntity<Customer> customer(Long custId){
        System.out.println("GetCustomer Request Received. CustId:"+custId);
            return customerService.customer(custId).map(ResponseEntity::ok).orElseThrow(()->new CustomerNotFound("Customer Not found"));
    }
}
