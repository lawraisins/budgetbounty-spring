package com.hcltech.services;

import com.hcltech.models.Redemption;
import com.hcltech.models.User;
import com.hcltech.models.Reward;
import com.hcltech.repositories.RedemptionRepository;
import com.hcltech.repositories.UserRepository;
import com.hcltech.repositories.RewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RedemptionService {

    @Autowired
    private RedemptionRepository redemptionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RewardRepository rewardRepository;

    /**
     * Retrieve all redemptions for a user.
     */
    public List<Redemption> getRedemptionsForUser(Long userId) {
        return redemptionRepository.findByUserUserId(userId);
    }

    /**
     * Retrieve all redemptions by status.
     */
    public List<Redemption> getRedemptionsByStatus(String status) {
        return redemptionRepository.findByStatus(status);
    }

    /**
     * Redeem a reward.
     */
    @Transactional
    public Redemption redeemReward(Long userId, Long rewardId) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Reward> rewardOpt = rewardRepository.findById(rewardId);

        if (userOpt.isEmpty() || rewardOpt.isEmpty()) {
            throw new IllegalArgumentException("User or Reward not found.");
        }

        User user = userOpt.get();
        Reward reward = rewardOpt.get();

        // Check if user has enough points
        if (user.getTotalPoints() < reward.getPointsRequired()) {
            throw new IllegalStateException("Not enough points to redeem this reward.");
        }

        // Deduct points from user
        user.setTotalPoints(user.getTotalPoints() - reward.getPointsRequired());
        userRepository.save(user);

        // Create redemption record
        Redemption redemption = new Redemption(user, reward, LocalDate.now(), LocalDate.now().plusMonths(1), "Approved");
        return redemptionRepository.save(redemption);
    }
}
