package com.springboot.interview.basic.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="customer")
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "customer_type",nullable = false)
    private String customerType;

    @Column(name="email", nullable = true)
    private String emailId;


    @Column(name ="created_date", nullable = false,updatable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name ="updated_date", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedDate;

}
