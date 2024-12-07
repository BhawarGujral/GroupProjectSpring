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

    // Show the Admin Dashboard
    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("users", allUsers); // Pass all users to the dashboard
        return "adminDashboard"; // Points to admin-dashboard.html
    }

    // Show user management page
//    @GetMapping("/manage-users")
//    public String manageUsers(Model model) {
//        List<User> allUsers = userService.getAllUsers();
//        model.addAttribute("users", allUsers);
//        return "manage-users"; // Points to manage-users.html
//    }

    // Delete a user by ID
//    @PostMapping("/delete-user")
//    public String deleteUser(@RequestParam Long userId, Model model) {
//        userService.deleteUserById(userId);
//        model.addAttribute("successMessage", "User deleted successfully.");
//        return "redirect:/admin/manage-users"; // Redirect back to manage-users page
//    }

    // Add a new user (Admin-only)
    @GetMapping("/add-user")
    public String showAddUserForm() {
        return "add-user"; // Points to add-user.html
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
            return "redirect:/admin/manage-users"; // Redirect to manage-users after successful addition
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Failed to add user. Username might already exist.");
            return "add-user"; // Stay on the add-user page with error
        }
    }
}
