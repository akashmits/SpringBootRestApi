package com.springboot.interview.basic.car.controllertest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.interview.basic.car.enums.CarEngineType;
import com.springboot.interview.basic.car.requestPojo.CarPojo;
import com.springboot.interview.basic.car.requestPojo.Manufacture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CarManageControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void testHealthCheck() throws Exception {
        mockMvc.perform(get("/v1/car/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateCar() throws Exception {
        CarPojo carPojo = CarPojo.builder().licensePlate("UP-1234").manufacture(Manufacture.builder().manufactureName("Tesla").build())
                .engineType(CarEngineType.ELECTRIC).seatCount(5).build();


        String request= new ObjectMapper().writeValueAsString(carPojo);
        mockMvc.perform(post("/v1/car").content(request).contentType("application/json").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
}