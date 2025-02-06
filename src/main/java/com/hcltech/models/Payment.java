package com.hcltech.models;

import jakarta.persistence.*;
import java.time.*;

@Entity
@Table(name = "Payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_seq")
    @SequenceGenerator(name = "payment_seq", sequenceName = "payment_seq", allocationSize = 1)
    private Long paymentId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "billId", nullable = false)
    private Bill bill;

    @ManyToOne
    @JoinColumn(name = "bankAccountId", nullable = false)
    private BankAccount bankAccount;

    @Column(nullable = false)
    private LocalDate paymentDate; // Changed from LocalDateTime to LocalDate

    @Column(nullable = false)
    private double paymentAmount;

    @Column(nullable = false)
    private int pointsEarned;
    
 // Default constructor
    public Payment() {}

    // Parameterized constructor
    public Payment(User user, Bill bill, BankAccount bankAccount, LocalDate paymentDate, double paymentAmount, int pointsEarned) {
        this.user = user;
        this.bill = bill;
        this.bankAccount = bankAccount;
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
        this.pointsEarned = pointsEarned;
    }

    // Getters and Setters
    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public LocalDate getPaymentDate() { // Updated getter
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) { // Updated setter
        this.paymentDate = paymentDate;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public int getPointsEarned() {
        return pointsEarned;
    }

    public void setPointsEarned(int pointsEarned) {
        this.pointsEarned = pointsEarned;
    }
    
    
    
}
