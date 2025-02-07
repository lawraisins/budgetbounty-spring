package com.hcltech.repositories;

import com.hcltech.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // Find user by username (used for authentication)
    Optional<User> findByUsername(String username);
    
    // Check if a username already exists
    boolean existsByUsername(String username);

    // Update user points (adding/subtracting points)
    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.totalPoints = :points WHERE u.userId = :userId")
    void updateUserPoints(Long userId, int points);
    
    
    // Check if user is an admin
    @Query("SELECT u.isAdmin FROM User u WHERE u.userId = :userId")
    boolean isUserAdmin(Long userId);
}
