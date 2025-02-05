package com.hcltech.controllers;

import com.hcltech.models.Reward;
import com.hcltech.services.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rewards")
public class RewardController {

    @Autowired
    private RewardService rewardService;

    // Get all rewards
    @GetMapping
    public ResponseEntity<List<Reward>> getAllRewards() {
        return ResponseEntity.ok(rewardService.getAllRewards());
    }

    // Redeem a reward
    @PostMapping("/redeem")
    public ResponseEntity<String> redeemReward(@RequestParam Long userId, @RequestParam Long rewardId) {
        return ResponseEntity.ok(rewardService.redeemReward(userId, rewardId));
    }
}
