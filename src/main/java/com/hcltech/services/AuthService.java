package com.hcltech.services;

import com.hcltech.models.User;
import com.hcltech.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Register a new user.
     */
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Get user by ID.
     */
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    /**
     * Check if a user exists.
     */
    public boolean userExists(Long userId) {
        return userRepository.existsById(userId);
    }

    /**
     * Authenticate user login by checking username and password.
     */
    public Optional<User> authenticateUser(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user;
        }
        return Optional.empty();
    }

    /**
     * Update user points (for payments and reward redemptions).
     */
    @Transactional
    public void updateUserPoints(Long userId, int points) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setTotalPoints(user.getTotalPoints() + points);
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("User not found.");
        }
    }

    /**
     * Check if user has enough points for redemption.
     */
    public boolean hasEnoughPoints(Long userId, int requiredPoints) {
        Optional<User> userOpt = userRepository.findById(userId);
        return userOpt.isPresent() && userOpt.get().getTotalPoints() >= requiredPoints;
    }
    
    // New method to check if a user is an admin
    public boolean isUserAdmin(Long userId) {
        return userRepository.isUserAdmin(userId);
    }
    
 // Get all users for selection in admin panel
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
