package com.hcltech.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    private Long userId;
    
    @Column(nullable = false, unique = true, length = 100)
    private String username; // Added username field

    @Column(nullable = false, length = 255)
    private String password; // Added password field

    @Column(nullable = false, length = 255)
    private String firstName;

    @Column(nullable = false, length = 255)
    private String lastName;

    @Column(nullable = false)
    private int totalPoints;

    @Column(nullable = false)
    private double accountBalance;

}
