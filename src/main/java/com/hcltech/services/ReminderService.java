package com.hcltech.services;

import com.hcltech.models.Reminder;
import com.hcltech.repositories.ReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReminderService {

    @Autowired
    private ReminderRepository reminderRepository;

    /**
     * Retrieve all reminders for a user.
     */
    public List<Reminder> getRemindersForUser(Long userId) {
        return reminderRepository.findByUserUserId(userId);
    }

    /**
     * Retrieve pending reminders that are due today.
     */
    public List<Reminder> getDueReminders() {
        LocalDateTime startDate = LocalDateTime.now().with(LocalTime.MIN); // Start of today
        LocalDateTime endDate = LocalDateTime.now().with(LocalTime.MAX);   // End of today
        return reminderRepository.findPendingRemindersDueToday(startDate, endDate);
    }

    /**
     * Retrieve a reminder for a specific bill and user.
     */
    public Reminder getReminderForBill(Long userId, Long billId) {
        return reminderRepository.findReminderForBill(userId, billId);
    }

    /**
     * Create or update a reminder.
     */
    public Reminder saveReminder(Reminder reminder) {
        return reminderRepository.save(reminder);
    }

    /**
     * Delete a reminder by ID.
     */
    public void deleteReminder(Long reminderId) {
        Optional<Reminder> reminder = reminderRepository.findById(reminderId);
        if (reminder.isEmpty()) {
            throw new IllegalArgumentException("Reminder with ID " + reminderId + " not found.");
        }
        reminderRepository.deleteById(reminderId);
    }
    
//  /**
//  * Mark a reminder as sent.
//  */
// public Reminder markReminderAsSent(Long reminderId) {
//     Optional<Reminder> reminderOptional = reminderRepository.findById(reminderId);
//
//     if (reminderOptional.isPresent()) {
//         Reminder reminder = reminderOptional.get();
//         reminder.setNotificationStatus("Sent");
//         return reminderRepository.save(reminder);
    
}
