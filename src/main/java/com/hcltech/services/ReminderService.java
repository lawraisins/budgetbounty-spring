package com.hcltech.services;

import com.hcltech.models.Reminder;
import com.hcltech.repositories.ReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReminderService {

    @Autowired
    private ReminderRepository reminderRepository;

    /**
     * Retrieve reminders for a user.
     */
    public List<Reminder> getRemindersForUser(Long userId) {
        return reminderRepository.findByUserUserId(userId);
    }

    /**
     * Retrieve pending reminders that are due today.
     */
    public List<Reminder> getDueReminders() {
        return reminderRepository.findPendingRemindersDueToday();
    }

    /**
     * Create or update a reminder.
     */
    public Reminder saveReminder(Reminder reminder) {
        Reminder existingReminder = reminderRepository.findReminderForBill(
                reminder.getUser().getUserId(), 
                reminder.getBill().getBillId()
        );

        if (existingReminder != null) {
            existingReminder.setReminderDateTime(reminder.getReminderDateTime());
            existingReminder.setNotificationStatus(reminder.getNotificationStatus());
            return reminderRepository.save(existingReminder);
        }

        return reminderRepository.save(reminder);
    }


    /**
     * Delete a reminder.
     */
    public void deleteReminder(Long reminderId) {
        if (!reminderRepository.existsById(reminderId)) {
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
//     } else {
//         throw new IllegalArgumentException("Reminder not found.");
//     }
// }
    
}
