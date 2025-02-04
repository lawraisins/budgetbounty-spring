package com.hcltech.services;

import com.hcltech.models.Reminder;
import com.hcltech.repositories.ReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
        reminder.setReminderDateTime(LocalDateTime.now());
        return reminderRepository.save(reminder);
    }
}
