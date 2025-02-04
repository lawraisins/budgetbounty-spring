package com.hcltech.repositories;

import com.hcltech.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // Find user by username (used for authentication)
    Optional<User> findByUsername(String username);
    
    // Check if a username already exists
    boolean existsByUsername(String username);
}
