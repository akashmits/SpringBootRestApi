package com.springboot.interview.basic.repositories;

import com.springboot.interview.basic.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
