package com.hcltech.models;

import jakarta.persistence.*;
import java.time.*;

@Entity
@Table(name = "Redemptions")
public class Redemption {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "redemption_seq")
    @SequenceGenerator(name = "redemption_seq", sequenceName = "redemption_seq", allocationSize = 1)
    private Long redemptionId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "rewardId", nullable = false)
    private Reward reward;

    @Column(nullable = false)
    private LocalDate redemptionDate; // Changed from LocalDateTime to LocalDate

    @Column(nullable = false)
    private LocalDate expiryDate; // Changed from LocalDateTime to LocalDate

    @Column(nullable = false, length = 255)
    private String status; // Pending, Approved, Rejected

    // Default constructor
    public Redemption() {}

    // Parameterized constructor
    public Redemption(User user, Reward reward, LocalDate redemptionDate, LocalDate expiryDate, String status) {
        this.user = user;
        this.reward = reward;
        this.redemptionDate = redemptionDate;
        this.expiryDate = expiryDate;
        this.status = status;
    }

    // Getters and Setters
    public Long getRedemptionId() {
        return redemptionId;
    }

    public void setRedemptionId(Long redemptionId) {
        this.redemptionId = redemptionId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Reward getReward() {
        return reward;
    }

    public void setReward(Reward reward) {
        this.reward = reward;
    }

    public LocalDate getRedemptionDate() { // Updated getter
        return redemptionDate;
    }

    public void setRedemptionDate(LocalDate redemptionDate) { // Updated setter
        this.redemptionDate = redemptionDate;
    }

    public LocalDate getExpiryDate() { // Updated getter
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) { // Updated setter
        this.expiryDate = expiryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
