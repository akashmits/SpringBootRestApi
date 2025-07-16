package com.springboot.interview.basic.car.iservice;

import com.springboot.interview.basic.car.entity.Car;
import com.springboot.interview.basic.car.requestPojo.CarPojo;

import java.util.Optional;

public interface ICarManagerService {
    public Car createCar(CarPojo carPojo);
    public Optional<Car> findByCarId(Long carId);
    public Optional<Car> findByLicencePlateNo(String licensePlateNo);

}
