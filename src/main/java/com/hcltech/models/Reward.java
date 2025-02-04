package com.hcltech.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Rewards")
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reward_seq")
    @SequenceGenerator(name = "reward_seq", sequenceName = "reward_seq", allocationSize = 1)
    private Long rewardId;

    @Column(nullable = false, length = 255)
    private String rewardName;

    @Column(nullable = false)
    private int pointsRequired;

    // Default constructor
    public Reward() {}

    // Parameterized constructor
    public Reward(String rewardName, int pointsRequired) {
        this.rewardName = rewardName;
        this.pointsRequired = pointsRequired;
    }

    // Getters and Setters
    public Long getRewardId() {
        return rewardId;
    }

    public void setRewardId(Long rewardId) {
        this.rewardId = rewardId;
    }

    public String getRewardName() {
        return rewardName;
    }

    public void setRewardName(String rewardName) {
        this.rewardName = rewardName;
    }

    public int getPointsRequired() {
        return pointsRequired;
    }

    public void setPointsRequired(int pointsRequired) {
        this.pointsRequired = pointsRequired;
    }
}
