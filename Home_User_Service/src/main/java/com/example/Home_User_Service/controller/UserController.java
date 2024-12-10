package com.example.Home_User_Service.controller;

import com.example.Home_User_Service.service.UserService;
import com.example.Home_User_Service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam("roles") String[] roles,
            Model model) {
        try {
            userService.registerUser(new User(null, username, password, Set.of(roles)));
            model.addAttribute("successMessage", "Registration successful!");
            return "redirect:/users/login";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "User already exists.");
            return "register";
        }
    }

    @GetMapping("/login")
    public String showLoginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "Invalid username or password.");
        }
        return "login";
    }

    @GetMapping("/dashboard")
    public String userDashboard(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("Accessing dashboard for user: " + username);
        model.addAttribute("username", username);

        return "userDashboard";
    }

}



