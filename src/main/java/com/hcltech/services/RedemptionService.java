package com.hcltech.services;

import com.hcltech.models.Redemption;
import com.hcltech.repositories.RedemptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RedemptionService {

    @Autowired
    private RedemptionRepository redemptionRepository;

    /**
     * Retrieve all redemptions for a user.
     */
    public List<Redemption> getRedemptionsForUser(Long userId) {
        return redemptionRepository.findByUserUserId(userId);
    }

    /**
     * Redeem a reward.
     */
    public Redemption redeemReward(Redemption redemption) {
        redemption.setRedemptionDate(LocalDateTime.now());
        redemption.setStatus("Pending");
        return redemptionRepository.save(redemption);
    }
}
