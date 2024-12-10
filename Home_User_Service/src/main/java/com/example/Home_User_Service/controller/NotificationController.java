package com.example.Home_User_Service.controller;

import com.example.Home_User_Service.model.Notification;
import com.example.Home_User_Service.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notifications")
    public String showNotificationsPage() {
        return "notifications";
    }

    @ResponseBody
    @GetMapping("/api/notifications")
    public List<Notification> getNotificationsForCurrentUser() {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        return notificationService.getNotificationsForUser(currentUser);
    }

    @PostMapping("/api/notifications/{id}/mark-as-read")
    @ResponseBody
    public void markAsRead(@PathVariable Long id) {
        notificationService.markAsRead(id);
    }

    @PostMapping("/api/notifications/{id}/delete")
    @ResponseBody
    public void deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
    }
}
