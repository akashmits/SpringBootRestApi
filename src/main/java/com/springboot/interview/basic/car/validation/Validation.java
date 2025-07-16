package com.springboot.interview.basic.car.validation;

import com.springboot.interview.basic.car.exceptions.InputValidationException;
import com.springboot.interview.basic.car.requestPojo.CarPojo;
import org.springframework.stereotype.Component;

@Component
public class Validation {

    public void carValidation(CarPojo carPojo){
        if(carPojo==null)
            throw new InputValidationException("Car Request Object is null, please chekc ");

        if(carPojo.getLicensePlate() ==null)
            throw new InputValidationException("In Car Request Object License Plate Number is null");
    }
}
