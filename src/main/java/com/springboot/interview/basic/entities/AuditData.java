package com.springboot.interview.basic.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "audit_data")
@Data
public class AuditData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="custId",nullable = false)
    private Long custId;

    @Column(name = "customerType",nullable = false,columnDefinition = "REGULAR")
    private String customerType;

    @Column(name ="createdDate", nullable = false,insertable = true,updatable = false)
    private Date createdDate;

    @Column(name ="updatedDate", nullable = false,insertable = true,updatable = true)
    private Date updatedDate;

}
