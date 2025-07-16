package com.springboot.interview.basic.car.controllertest;

import com.springboot.interview.basic.car.controller.CarManageController;
import com.springboot.interview.basic.car.entity.Car;
import com.springboot.interview.basic.car.enums.CarEngineType;
import com.springboot.interview.basic.car.iservice.ICarManagerService;
import com.springboot.interview.basic.car.requestPojo.CarPojo;
import com.springboot.interview.basic.car.requestPojo.Manufacture;
import com.springboot.interview.basic.car.service.CarManagerService;
import com.springboot.interview.basic.car.validation.Validation;
import com.springboot.interview.basic.responses.ErrorResponse;
import jakarta.inject.Inject;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CarControllerTest {

    @Mock
    private CarManagerService carManagerService;

    @Mock
    private Validation validation;

    @InjectMocks
    private CarManageController carManageController;


    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createCarTest_test1() throws Exception {
        CarPojo carPojo = CarPojo.builder().licensePlate("UP-1234").manufacture(Manufacture.builder().manufactureName("Tesla").build())
                .engineType(CarEngineType.ELECTRIC).seatCount(5).build();

        Car car = Car.builder().licensePlate("UP-1234").manufacture(com.springboot.interview.basic.car.entity.Manufacture.builder().name("Tesla").build()).
        carType(CarEngineType.ELECTRIC).seatCount(5).build();

        when(carManagerService.createCar(carPojo)).thenReturn(car);
        ResponseEntity<Car> responseEntity=carManageController.createCar(carPojo);

        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        assertEquals(car,responseEntity.getBody());

        verify(validation,times(1)).carValidation(carPojo);
        verify(carManagerService,times(1)).createCar(carPojo);

    }

    @Test
    public void createCarTest_LicensePlateMissing() throws Exception {
        CarPojo carPojo = CarPojo.builder().manufacture(Manufacture.builder().manufactureName("Tesla").build())
                .engineType(CarEngineType.ELECTRIC).seatCount(5).build();

        ResponseEntity<Car> responseEntity = carManageController.createCar(carPojo);

    }
}
