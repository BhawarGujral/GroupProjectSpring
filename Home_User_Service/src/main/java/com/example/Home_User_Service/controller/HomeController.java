package com.example.Home_User_Service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Serve the Home Page
    @GetMapping("/")
    public String showHomePage() {
        return "home"; // Points to home.html in src/main/resources/templates
    }
}

