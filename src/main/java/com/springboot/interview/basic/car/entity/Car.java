package com.springboot.interview.basic.car.entity;

import com.springboot.interview.basic.car.enums.CarEngineType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;

@Entity
@Table(name = "car")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Car {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="license_plate",nullable = false)
    private String licensePlate;

    @Column(name = "seat_count",nullable = false)
    private int seatCount;

    @Column(name="convertible")
    private boolean convertible;

    @Column(name = "rating")
    private int ratings;

    @Enumerated(EnumType.STRING)
    @Column(name="engine_type")
    private CarEngineType carType;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacture_id", referencedColumnName = "id",nullable = false)
    private Manufacture manufacture;

    @Column(name = "created_date")
    @CreationTimestamp
    private ZonedDateTime zonedDateTime;
}
