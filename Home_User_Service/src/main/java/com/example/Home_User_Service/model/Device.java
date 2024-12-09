package com.example.Home_User_Service.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "devices") // Hibernate will create a table named "devices"
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String status; // ON or OFF

    @Column(nullable = false)
    private String owner; // Username of the device owner
}
