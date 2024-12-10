package com.example.Home_User_Service.repository;

<<<<<<< Updated upstream
public class NotificationRepository {
=======
import com.example.Home_User_Service.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByRecipient(String recipient); // Fetch notifications for a user
>>>>>>> Stashed changes
}
