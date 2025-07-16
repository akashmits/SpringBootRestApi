package com.springboot.interview.basic.car.repositories;

import com.springboot.interview.basic.car.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}
