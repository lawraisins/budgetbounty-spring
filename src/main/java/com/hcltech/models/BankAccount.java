package com.hcltech.models;

import jakarta.persistence.*;

@Entity
@Table(name = "BankAccount")

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
    
 // Default constructor
    public BankAccount() {}

    // Parameterized constructor
    public BankAccount(User user, String bankAccountNumber) {
        this.user = user;
        this.bankAccountNumber = bankAccountNumber;
    }

    // Getters and Setters
    public Long getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(Long bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

}
