package com.example.Home_User_Service.service;

<<<<<<< Updated upstream
=======
import com.example.Home_User_Service.model.Notification;
import com.example.Home_User_Service.repository.NotificationRepository;
>>>>>>> Stashed changes
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
<<<<<<< Updated upstream
=======
import java.util.List;
>>>>>>> Stashed changes

@Service
public class NotificationService {

    @Autowired
<<<<<<< Updated upstream
    private JavaMailSender mailSender;

    private String notificationEmail = "admin@smarthome.com"; // Admin or system alert email address

   private String senderEmail="kgurpret@gmail.com";

    public void sendDeviceStatusAlert(String deviceName, String status) throws MessagingException {
        String subject = "Device Status Alert";
        String text = "Device: " + deviceName + " has changed status to: " + status;

        sendEmail(senderEmail,subject, text);
    }

    public void sendErrorNotification(String errorMessage) throws MessagingException {
        String subject = "Error Notification";
        String text = "An error occurred: " + errorMessage;

        sendEmail(senderEmail,subject, text);
    }

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}
=======
    private NotificationRepository notificationRepository;

    // Create a notification
    public Notification createNotification(String message, String recipient) {
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setRecipient(recipient);
        return notificationRepository.save(notification);
    }

    // Fetch notifications for a user
    public List<Notification> getNotificationsForUser(String recipient) {
        return notificationRepository.findByRecipient(recipient);
    }

    // Mark a notification as read
    public void markNotificationAsRead(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new IllegalArgumentException("Notification not found"));
        notification.setRead(true);
        notificationRepository.save(notification);
    }
    public void markAsRead(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Notification not found"));
        notification.setRead(true);
        notificationRepository.save(notification);
    }
    public void deleteNotification(Long notificationId) {
        notificationRepository.deleteById(notificationId);
    }


}

>>>>>>> Stashed changes
