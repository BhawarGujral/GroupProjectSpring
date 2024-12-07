package com.example.Home_User_Service.controller;

import com.example.Home_User_Service.model.User;
import com.example.Home_User_Service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Show the registration page
    @GetMapping("/register")
    public String showRegistrationPage() {
        return "register"; // Points to register.html in templates
    }

    // Handle user registration
    @PostMapping("/register")
    public String registerUser(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam("roles") String[] roles,
            Model model) {
        try {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password); // Store plain-text password
            user.setRoles(Set.of(roles)); // Convert roles array to Set
            userService.registerUser(user);
            model.addAttribute("successMessage", "Registration successful!");
            return "redirect:/users/login"; // Redirect to login page after registration
        } catch (Exception e) {
            model.addAttribute("errorMessage", "User already exists.");
            return "register"; // Stay on the registration page with error
        }
    }

    // Show the login page
    @GetMapping("/login")
    public String showLoginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "Invalid username or password.");
        }
        return "login"; // Points to login.html in templates
    }
}
