package com.hcltech.repositories;

import com.hcltech.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    
    // Get all payments for a specific user
    List<Payment> findByUserUserId(Long userId);

    // Find all payments made within the last X days
    @Query("SELECT p FROM Payment p WHERE p.paymentDateTime >= CURRENT_DATE - :days")
    List<Payment> findPaymentsWithinLastDays(int days);
}
