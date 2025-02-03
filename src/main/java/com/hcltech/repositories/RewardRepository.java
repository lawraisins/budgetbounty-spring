package com.hcltech.repositories;

import com.hcltech.models.Reward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RewardRepository extends JpaRepository<Reward, Long> {
    
    // Find rewards by points required
    List<Reward> findByPointsRequired(int pointsRequired);

    // Find all rewards with points requirement less than or equal to a given amount
    @Query("SELECT r FROM Reward r WHERE r.pointsRequired <= :points")
    List<Reward> findRewardsWithinPoints(int points);
}
