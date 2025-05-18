package com.springboot.interview.basic.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "custId", referencedColumnName = "id",nullable = false) // FK column
    private Customer customer;

    @Column(name ="created_date", nullable = false,updatable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name ="updated_date", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedDate;

    @Column(name="amount",nullable = false)
    private String amount;

    @Column(name="discounted_amount",nullable = true)
    private String discountedAmount;
}