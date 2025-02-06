package com.hcltech.controllers;

import com.hcltech.models.User;
import com.hcltech.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // Enable CORS for React frontend
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * User Registration Endpoint
     */
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User newUser = authService.registerUser(user);
        return ResponseEntity.ok(newUser);
    }

    /**
     * User Login Authentication
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        boolean authenticated = authService.authenticateUser(user.getUsername(), user.getPassword());
        return authenticated ? ResponseEntity.ok("Login successful") :
                ResponseEntity.badRequest().body("Invalid username or password");
    }

    /**
     * Get User Details by ID
     */
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable("userId") Long userId) {
        Optional<User> user = authService.getUserById(userId);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Update User Points
     * Used when making payments or redeeming rewards.
     */
    @PutMapping("/{userId}/points")
    public ResponseEntity<String> updateUserPoints(@PathVariable Long userId, @RequestParam int points) {
        try {
            authService.updateUserPoints(userId, points);
            return ResponseEntity.ok("User points updated successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Check if user has enough points for redemption.
     */
    @GetMapping("/{userId}/check-points")
    public ResponseEntity<Boolean> checkUserPoints(@PathVariable Long userId, @RequestParam int requiredPoints) {
        boolean hasEnough = authService.hasEnoughPoints(userId, requiredPoints);
        return ResponseEntity.ok(hasEnough);
    }
}
