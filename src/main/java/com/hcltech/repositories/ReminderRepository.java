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
    @Query("SELECT r FROM Reminder r WHERE r.notificationStatus = 'Pending' AND DATE(r.reminderDateTime) = CURRENT_DATE")
    List<Reminder> findPendingRemindersDueToday();
}
