package com.example.Home_User_Service.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class NotificationService {

    @Autowired
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
