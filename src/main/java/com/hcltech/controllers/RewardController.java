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

    /**
     * Retrieve all available rewards.
     */
    @GetMapping("/all")
    public ResponseEntity<List<Reward>> getAllRewards() {
        return ResponseEntity.ok(rewardService.getAllRewards());
    }

    /**
     * Get a specific reward by ID.
     */
    @GetMapping("/{rewardId}")
    public ResponseEntity<Reward> getRewardById(@PathVariable("rewardId") Long rewardId) {
        try {
            return ResponseEntity.ok(rewardService.getRewardById(rewardId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    /**
     * Get rewards that a user can redeem with their total points balance.
     */
    @GetMapping("/redeemable/{userPoints}")
    public ResponseEntity<List<Reward>> getRedeemableRewards(@PathVariable("userPoints") int userPoints) {
        return ResponseEntity.ok(rewardService.getRedeemableRewards(userPoints));
    }

    /**
     * Redeem a reward using user points.
     */
    @PostMapping("/redeem")
    public ResponseEntity<String> redeemReward(@RequestParam("userId") Long userId, @RequestParam("rewardId") Long rewardId) {
        String response = rewardService.redeemReward(userId, rewardId);
        if (response.equals("Not enough points for redemption.")) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }


    /**
     * Add a new reward (Admin Functionality).
     */
    @PostMapping("/add")
    public ResponseEntity<Reward> addReward(@RequestBody Reward reward) {
        return ResponseEntity.ok(rewardService.addReward(reward));
    }

    /**
     * Delete a reward by ID (Admin Functionality).
     */
    @DeleteMapping("/{rewardId}")
    public ResponseEntity<String> deleteReward(@PathVariable("rewardId") Long rewardId) {
        try {
            rewardService.deleteReward(rewardId);
            return ResponseEntity.ok("Reward deleted successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
