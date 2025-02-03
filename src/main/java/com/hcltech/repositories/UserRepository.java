package com.hcltech.repositories;

import com.hcltech.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // Find user by first name
    User findByFirstName(String firstName);
    
    // Find user by last name
    User findByLastName(String lastName);
}
