package com.springboot.interview.basic.service;

import com.springboot.interview.basic.customexceptions.CustomerNotFound;
import com.springboot.interview.basic.entities.Customer;
import com.springboot.interview.basic.entities.Order;
import com.springboot.interview.basic.iservice.ICheckCounterAndUpdateMemberShip;
import com.springboot.interview.basic.pojo.CustomerType;
import com.springboot.interview.basic.pojo.OrderPojo;
import com.springboot.interview.basic.repositories.OrderRepository;
import com.springboot.interview.basic.iservice.ICustomerService;
import com.springboot.interview.basic.iservice.IOrderedService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OrderedService implements IOrderedService {

    private OrderRepository orderRepository;
    private ICustomerService customerService;
    private ICheckCounterAndUpdateMemberShip checkCounterAndUpdateMemberShip;

    public OrderedService( OrderRepository orderRepository,CustomerService customerService,
                           CheckCounterAndUpdateMemberShip checkCounterAndUpdateMemberShip){
     this.orderRepository=orderRepository;
     this.customerService=customerService;
     this.checkCounterAndUpdateMemberShip=checkCounterAndUpdateMemberShip;
    }
    @Override
    public Order order(OrderPojo orderPojo) {
        Order order=creatOrderObj(orderPojo);
        checkCounterAndUpdateMemberShip.incrementCounterAndNotify(order.getCustomer().getId(),order.getCustomer().getEmailId());
        return order;
    }

    @Override
    public Optional<Order> order(Long orderId) {
        return orderRepository.findById(orderId);
    }

    private Order creatOrderObj(OrderPojo orderPojo){

        Customer customer = customerService.customer(orderPojo.getCustId())
                .orElseThrow(() -> new CustomerNotFound("Customer not found with ID: " + orderPojo.getCustId()));


        Order order = new Order();
        order.setCustomer(customer);
        order.setAmount(orderPojo.getAmount());
        if (CustomerType.GOLD.equals(customer.getCustomerType())){
            order.setDiscountedAmount(Double.parseDouble(order.getAmount())*(.1)+"");
        }else if(CustomerType.PREMIUM.equals(customer.getCustomerType())){
            order.setDiscountedAmount(Double.parseDouble(order.getAmount())*(.2)+"");
        }
       // order.setCreatedDate(LocalDateTime.now());
        //order.setUpdatedDate(LocalDateTime.now());

        Order newOrder= orderRepository.save(order);
        System.out.println("Order Created :"+newOrder);
        return  newOrder;



    }
}
