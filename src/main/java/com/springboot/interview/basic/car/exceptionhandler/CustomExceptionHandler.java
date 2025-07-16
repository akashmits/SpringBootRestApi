package com.springboot.interview.basic.car.exceptionhandler;

import com.springboot.interview.basic.car.exceptions.CarNotFound;
import com.springboot.interview.basic.car.exceptions.InputValidationException;
import com.springboot.interview.basic.responses.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {

   @ExceptionHandler(CarNotFound.class)
   public ResponseEntity<ErrorResponse> carNotFoundException(CarNotFound carNotFound){
      return ResponseEntity.status(HttpStatus.OK).body(ErrorResponse.builder().errorCode("1002").message(carNotFound.getMessage()).build());
   }

   @ExceptionHandler({InputValidationException.class})
   public ResponseEntity<ErrorResponse> carInputValidation(InputValidationException input){
      return  ResponseEntity.status(HttpStatus.OK).body(ErrorResponse.builder().errorCode("1001").message(input.getMessage()).build());
   }
}
