package com.hcltech.models;

import jakarta.persistence.*;
import java.time.*;

@Entity
@Table(name = "Bills")

public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bill_seq")
    @SequenceGenerator(name = "bill_seq", sequenceName = "bill_seq", allocationSize = 1)
    private Long billId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private LocalDate dueDate; //needa change to date type

    @Column(nullable = false)
    private boolean recurring; // True/False //maybe can use boolean here?

    @Column(nullable = false, length = 10)
    private String billStatus = "unpaid"; // Paid / Unpaid
    
 // Default constructor
    public Bill() {}

    // Parameterized constructor
    public Bill(User user, double amount, LocalDate dueDate, boolean recurring, String billStatus) {
        this.user = user;
        this.amount = amount;
        this.dueDate = dueDate;
        this.recurring = recurring;
        this.billStatus = billStatus;
    }

    // Getters and Setters
    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isRecurring() {
        return recurring;
    }

    public void setRecurring(boolean recurring) {
        this.recurring = recurring;
    }

    public String getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus;
    }
}
