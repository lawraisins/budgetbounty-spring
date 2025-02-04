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

    @Autowired
    private AuthService authService; // Used for deducting points

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
        return rewardRepository.findById(rewardId)
                .orElseThrow(() -> new IllegalArgumentException("Reward with ID " + rewardId + " not found."));
    }

    /**
     * Find rewards within a user's total points balance.
     */
    public List<Reward> getRedeemableRewards(int userPoints) {
        return rewardRepository.findRewardsWithinPoints(userPoints);
    }

    /**
     * Redeem a reward (deduct user points).
     */
    public String redeemReward(Long userId, Long rewardId) {
        Reward reward = getRewardById(rewardId);

        if (!authService.hasEnoughPoints(userId, reward.getPointsRequired())) {
            return "Not enough points for redemption.";
        }

        // Deduct points
        authService.updateUserPoints(userId, -reward.getPointsRequired());

        return "Reward redeemed successfully!";
    }

    /**
     * Add a new reward (admin functionality).
     */
    public Reward addReward(Reward reward) {
        return rewardRepository.save(reward);
    }

    /**
     * Delete a reward by ID.
     */
    public void deleteReward(Long rewardId) {
        if (!rewardRepository.existsById(rewardId)) {
            throw new IllegalArgumentException("Reward with ID " + rewardId + " not found.");
        }
        rewardRepository.deleteById(rewardId);
    }
}
