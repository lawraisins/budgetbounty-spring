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

    // Get all payments linked to a specific bank account
    List<Payment> findByBankAccountBankAccountId(Long bankAccountId);

    // Find all payments made within the last X days
    @Query("SELECT p FROM Payment p WHERE p.paymentDateTime >= :startDate")
    List<Payment> findPaymentsWithinLastDays(LocalDateTime startDate);

    // Find all paid payments for a specific user
    @Query("SELECT p FROM Payment p WHERE p.user.userId = :userId AND p.bill.billStatus = 'Paid'")
    List<Payment> findPaidPaymentsForUser(Long userId);
}
