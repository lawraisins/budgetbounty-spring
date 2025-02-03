package com.hcltech.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "BankAccount")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bank_account_seq")
    @SequenceGenerator(name = "bank_account_seq", sequenceName = "bank_account_seq", allocationSize = 1)
    private Long bankAccountId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Column(nullable = false, unique = true)
    private String bankAccountNumber;

}
