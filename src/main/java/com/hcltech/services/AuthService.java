package com.hcltech.services;

import com.hcltech.models.User;
import com.hcltech.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

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
}
