package com.springboot.interview.basic.pojo;

import lombok.*;


@Data
@ToString
public class CustomerPojo {
    String name;
    CustomerType customerType;
    String emailId;
}
