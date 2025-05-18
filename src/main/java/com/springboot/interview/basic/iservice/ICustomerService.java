package com.springboot.interview.basic.iservice;

import com.springboot.interview.basic.entities.Customer;
import com.springboot.interview.basic.pojo.CustomerPojo;

import java.util.Optional;

public interface ICustomerService {
    public Customer customer(CustomerPojo customer);
    public Optional<Customer> customer(long custId);
    public Customer customer(Customer customer);
}
