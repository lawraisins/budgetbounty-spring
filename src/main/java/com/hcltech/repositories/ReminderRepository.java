package com.hcltech.repositories;

import com.hcltech.models.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder, Long> {
    
    // Get reminders for a specific user
    List<Reminder> findByUserUserId(Long userId);

    // Find pending reminders that are due today
    @Query("SELECT r FROM Reminder r WHERE r.notificationStatus = 'Pending' " +
           "AND r.reminderDateTime BETWEEN CURRENT_DATE AND CURRENT_DATE + 1")
    List<Reminder> findPendingRemindersDueToday();

    // Find a reminder for a specific bill and user
    @Query("SELECT r FROM Reminder r WHERE r.user.userId = :userId AND r.bill.billId = :billId")
    Reminder findReminderForBill(Long userId, Long billId);
}
