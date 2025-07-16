package com.springboot.interview.basic.car.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;

@Entity
@Table(name = "manufacture")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Manufacture {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name="created_date")
    @CreationTimestamp
    private ZonedDateTime zonedDateTime;

}
