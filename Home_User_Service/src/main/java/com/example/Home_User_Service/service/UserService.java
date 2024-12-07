package com.example.Home_User_Service.service;

import com.example.Home_User_Service.model.User;
import com.example.Home_User_Service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Register a user without adding {noop}
    public User registerUser(User user) {
        return userRepository.save(user); // Save password as is
    }

    // Authenticate user by matching raw passwords
    public boolean authenticateUser(String username, String rawPassword) {
        return userRepository.findByUsername(username)
                .map(user -> user.getPassword().equals(rawPassword)) // Compare raw passwords
                .orElse(false);
    }
    // Add this method to fetch all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Add this method to delete a user by ID
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }

}
