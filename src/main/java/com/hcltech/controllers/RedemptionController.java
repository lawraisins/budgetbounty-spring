package com.hcltech.controllers;

import com.hcltech.models.Redemption;
import com.hcltech.services.RedemptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/redemptions")
public class RedemptionController {

    @Autowired
    private RedemptionService redemptionService;

    // Get all redemptions for a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Redemption>> getRedemptionsForUser(@PathVariable Long userId) {
        return ResponseEntity.ok(redemptionService.getRedemptionsForUser(userId));
    }

    // Redeem a reward
    @PostMapping
    public ResponseEntity<Redemption> redeemReward(@RequestBody Redemption redemption) {
        return ResponseEntity.ok(redemptionService.redeemReward(redemption));
    }
}
