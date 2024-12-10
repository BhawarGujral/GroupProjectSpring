package com.example.Home_User_Service.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String message; // Notification content

    @Column(nullable = false)
    private String recipient; // Username of the recipient

    @Column(nullable = false)
    private boolean isRead = false; // Whether the notification has been read
//
//    @Column(nullable = false)
//    private String deviceName; // Name of the device
//
//    @Column(nullable = false)
//    private String status; // Status of the device (e.g., ON, OFF)

    @Column(nullable = false)
    private LocalDateTime timestamp = LocalDateTime.now(); // When the notification was created
}
