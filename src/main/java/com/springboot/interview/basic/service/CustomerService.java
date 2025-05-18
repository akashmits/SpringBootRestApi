package com.springboot.interview.basic.service;

import com.springboot.interview.basic.entities.Customer;
import com.springboot.interview.basic.pojo.CustomerPojo;
import com.springboot.interview.basic.pojo.CustomerType;
import com.springboot.interview.basic.repositories.CustomerRepository;
import com.springboot.interview.basic.iservice.ICustomerService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {
    CustomerRepository customerRepository;
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository=customerRepository;
    }
    @Override
    public Customer customer(CustomerPojo customerPojo) {
        Customer customer=createCustomer(customerPojo);
        return  customer;
    }

    private Customer createCustomer(CustomerPojo customerPojo){
        Customer customer=new Customer();
        System.out.println("Customer Pojo name"+customerPojo.getName());
        customer.setCustomerType(customerPojo.getCustomerType() ==null ? CustomerType.REGULAR.name():customerPojo.getCustomerType().name());
        customer.setName(customerPojo.getName());
        customer.setEmailId(customerPojo.getEmailId());
        customer.setCreatedDate(LocalDateTime.now());
        customer.setUpdatedDate(LocalDateTime.now());

        System.out.println("Customer created :"+customer.getName());
        Customer cust=customerRepository.save(customer);
        return cust;

    }

    @Override
    public Optional<Customer> customer(long custId) {
        return customerRepository.findById(custId);
    }
}
