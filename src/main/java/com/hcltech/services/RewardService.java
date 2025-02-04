package com.hcltech.services;

import com.hcltech.models.Reward;
import com.hcltech.repositories.RewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RewardService {

    @Autowired
    private RewardRepository rewardRepository;

    /**
     * Retrieve all available rewards.
     */
    public List<Reward> getAllRewards() {
        return rewardRepository.findAll();
    }

    /**
     * Get a specific reward by ID.
     */
    public Reward getRewardById(Long rewardId) {
        return rewardRepository.findById(rewardId).orElse(null);
    }
}
