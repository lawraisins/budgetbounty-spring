package com.hcltech.controllers;

import com.hcltech.models.Redemption;
import com.hcltech.services.RedemptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // Enable CORS for React frontend
@RequestMapping("/redemptions")
public class RedemptionController {

    @Autowired
    private RedemptionService redemptionService;

    /**
     * Retrieve all redemptions for a specific user.
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Redemption>> getRedemptionsForUser(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(redemptionService.getRedemptionsForUser(userId));
    }

    /**
     * Retrieve all redemptions by status (Pending, Approved, Rejected).
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Redemption>> getRedemptionsByStatus(@PathVariable String status) {
        return ResponseEntity.ok(redemptionService.getRedemptionsByStatus(status));
    }

    /**
     * Redeem a reward using user points.
     */
    @PostMapping("/redeem")
    public ResponseEntity<?> redeemReward(@RequestParam("userId") Long userId, @RequestParam("rewardId") Long rewardId) {
        try {
            Redemption redemption = redemptionService.redeemReward(userId, rewardId);
            return ResponseEntity.ok(redemption);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
