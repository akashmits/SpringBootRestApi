package com.springboot.interview.basic.car.service;

import com.springboot.interview.basic.car.entity.Car;
import com.springboot.interview.basic.car.entity.Manufacture;
import com.springboot.interview.basic.car.iservice.ICarManagerService;
import com.springboot.interview.basic.car.repositories.CarRepository;
import com.springboot.interview.basic.car.requestPojo.CarPojo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarManagerService implements ICarManagerService {

    private CarRepository carRepository;

    public  CarManagerService(CarRepository carRepository){
        this.carRepository=carRepository;
    }
    @Override
    public Car createCar(CarPojo carPojo) {
        Car car = Car.builder().carType(carPojo.getEngineType()).seatCount(carPojo.getSeatCount())
                .manufacture(Manufacture.builder().name(carPojo.getManufacture().getManufactureName()).build()).licensePlate(carPojo.getLicensePlate())
                .convertible(Boolean.parseBoolean(carPojo.getConvertible())).build();
        car =carRepository.save(car);
        return car;
    }

    @Override
    public Optional<Car> findByCarId(Long carId) {
        return Optional.empty();
    }

    @Override
    public Optional<Car> findByLicencePlateNo(String licensePlateNo) {
        return Optional.empty();
    }
}
