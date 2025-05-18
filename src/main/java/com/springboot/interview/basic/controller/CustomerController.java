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
        try {
            // TODO : validation
            System.out.println("Request Received :"+customerPojo);
            Customer customer = customerService.customer(customerPojo);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } catch(Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping
    public ResponseEntity<Customer> customer(Long custId){
        try {
            // TODO : validation
            Optional<Customer> customer = customerService.customer(custId);

            customer.ifPresent(customer1 ->{
                throw new CustomerNotFound("Customer Not found");
            });

                return new ResponseEntity<>(customer.get(), HttpStatus.OK);

        } catch(Exception ex){
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }
}
