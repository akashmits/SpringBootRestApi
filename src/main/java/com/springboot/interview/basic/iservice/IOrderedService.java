package com.springboot.interview.basic.iservice;

import com.springboot.interview.basic.entities.Order;
import com.springboot.interview.basic.pojo.OrderPojo;

import java.util.Optional;

public interface IOrderedService {

    public Order order(OrderPojo orderPojo);
    public Optional<Order> order(Long orderId);

}
