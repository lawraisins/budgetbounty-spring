package com.hcltech.repositories;

import com.hcltech.models.Redemption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RedemptionRepository extends JpaRepository<Redemption, Long> {
    
    // Find redemptions by user
    List<Redemption> findByUserUserId(Long userId);

    // Find redemptions by status (Pending, Approved, Rejected)
    List<Redemption> findByStatus(String status);

    // Find all redemptions within a date range
    @Query("SELECT r FROM Redemption r WHERE r.redemptionDate BETWEEN :startDate AND :endDate")
    List<Redemption> findRedemptionsWithinDateRange(LocalDateTime startDate, LocalDateTime endDate);
}
