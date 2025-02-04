package com.hcltech.services;

import com.hcltech.models.User;
import com.hcltech.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public boolean authenticateUser(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.isPresent() && user.get().getPassword().equals(password);
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
}
