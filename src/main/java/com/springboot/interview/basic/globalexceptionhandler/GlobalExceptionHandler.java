package com.springboot.interview.basic.globalexceptionhandler;


import com.springboot.interview.basic.customexceptions.CustomerNotFound;
import com.springboot.interview.basic.customexceptions.OrderNotFound;
import com.springboot.interview.basic.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFound.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(CustomerNotFound ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().errorCode("1002").message(ex.getMessage()).timestamp(LocalDateTime.now()).build());
    }

    @ExceptionHandler(OrderNotFound.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(OrderNotFound ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().errorCode("1002").message(ex.getMessage()).timestamp(LocalDateTime.now()).build());
    }
}
