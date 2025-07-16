package com.springboot.interview.basic.car.controller;

import com.springboot.interview.basic.car.entity.Car;
import com.springboot.interview.basic.car.exceptions.CarNotFound;
import com.springboot.interview.basic.car.iservice.ICarManagerService;
import com.springboot.interview.basic.car.requestPojo.CarPojo;
import com.springboot.interview.basic.car.service.CarManagerService;
import com.springboot.interview.basic.car.validation.Validation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1/car")
public class CarManageController {

    private ICarManagerService iCarManagerService;

    private Validation validation;

    public CarManageController(CarManagerService iCarManagerService, Validation validation){
        this.iCarManagerService =iCarManagerService;
        this.validation =validation;
    }


    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody(required = true) final CarPojo car){
        validation.carValidation(car);
        Car newCar=iCarManagerService.createCar(car);
        return new ResponseEntity<>(newCar,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCar(@PathVariable  long id){
        Optional<Car> optionalCar= iCarManagerService.findByCarId(id);
        if(optionalCar.isPresent())
            return new ResponseEntity<>(optionalCar.get(),HttpStatus.OK);

        throw new CarNotFound("Car is not found. Id :"+id);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody(required = true) final CarPojo car){
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
