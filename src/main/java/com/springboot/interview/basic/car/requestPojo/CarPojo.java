package com.springboot.interview.basic.car.requestPojo;

import com.springboot.interview.basic.car.enums.CarEngineType;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CarPojo {
    private String licensePlate;
    private int seatCount;
    private String convertible;
    private int rating;
    private CarEngineType engineType;
    private Manufacture manufacture;
    private long carId;
}
