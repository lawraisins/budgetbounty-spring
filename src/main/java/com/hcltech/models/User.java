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

    @Column(nullable = false, length = 255)
    private String firstName;

    @Column(nullable = false, length = 255)
    private String lastName;

    private int totalPoints;

    @Column(nullable = false)
    private double accountBalance;

    @Column(nullable = false, unique = true, length = 15)
    private String phoneNumber;
}
