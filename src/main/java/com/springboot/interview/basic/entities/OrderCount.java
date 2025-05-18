package com.springboot.interview.basic.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="order_count")
@Data
public class OrderCount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="custId",unique = true,nullable = false)
    private Long custId;

    @Column(name="counter",nullable = false)
    private Long counter;
}
