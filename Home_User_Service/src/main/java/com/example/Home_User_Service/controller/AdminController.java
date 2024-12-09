package com.example.Home_User_Service.controller;

import com.example.Home_User_Service.model.User;
import com.example.Home_User_Service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("users", allUsers);
        return "adminDashboard";
    }

    @GetMapping("/add-user")
    public String showAddUserForm() {
        return "register";
    }

    @PostMapping("/add-user")
    public String addUser(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam("roles") String[] roles,
            Model model) {
        try {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setRoles(Set.of(roles));
            userService.registerUser(user);
            model.addAttribute("successMessage", "User added successfully!");
            return "redirect:/admin/dashboard";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Failed to add user. Username might already exist.");
            return "register";
        }
    }
}
