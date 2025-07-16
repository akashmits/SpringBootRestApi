package com.springboot.interview.basic.car.controllertest;

import com.springboot.interview.basic.car.controller.CarManageController;
import com.springboot.interview.basic.car.entity.Car;
import com.springboot.interview.basic.car.iservice.ICarManagerService;
import com.springboot.interview.basic.car.validation.Validation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarManageController.class)
public class CarManagerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICarManagerService carManagerService;

    @MockBean
    private Validation validation;

    @Test
    void testGetCar_NotFound_Returns404() throws Exception {
        when(carManagerService.findByCarId(1L)).thenReturn(java.util.Optional.empty());

        mockMvc.perform(get("/v1/car/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Car is not found. Id :1"));
    }

//    @Test
//    void testCreateCar_Returns200() throws Exception {
//        //CarPojo pojo = new CarPojo(); // You can fill fields and convert to JSON
//        String carJson = "{ \"licensePlate\": \"XYZ123\", \"seatCount\": \"4\" }";
//
//        doNothing().when(validation).carValidation(any(CarPojo.class));
//        when(carManagerService.createCar(any(CarPojo.class))).thenReturn(new Car());
//
//        mockMvc.perform(post("/v1/car")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(carJson))
//                .andExpect(status().isOk());
//    }
}

