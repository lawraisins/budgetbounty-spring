package com.hcltech.controllers;

import com.hcltech.models.Reminder;
import com.hcltech.services.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reminders")
public class ReminderController {

    @Autowired
    private ReminderService reminderService;

    /**
     * Retrieve all reminders for a specific user.
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Reminder>> getRemindersForUser(@PathVariable Long userId) {
        List<Reminder> reminders = reminderService.getRemindersForUser(userId);
        return ResponseEntity.ok(reminders);
    }

    /**
     * Retrieve pending reminders that are due today.
     */
    @GetMapping("/due-today")
    public ResponseEntity<List<Reminder>> getDueReminders() {
        List<Reminder> dueReminders = reminderService.getDueReminders();
        return ResponseEntity.ok(dueReminders);
    }

    /**
     * Retrieve a reminder for a specific bill and user.
     */
    @GetMapping("/bill")
    public ResponseEntity<Reminder> getReminderForBill(@RequestParam Long userId, @RequestParam Long billId) {
        Reminder reminder = reminderService.getReminderForBill(userId, billId);
        return reminder != null ? ResponseEntity.ok(reminder) : ResponseEntity.notFound().build();
    }

    /**
     * Create or update a reminder.
     */
    @PostMapping("/save")
    public ResponseEntity<Reminder> saveReminder(@RequestBody Reminder reminder) {
        Reminder savedReminder = reminderService.saveReminder(reminder);
        return ResponseEntity.ok(savedReminder);
    }

    /**
     * Delete a reminder by ID.
     */
    @DeleteMapping("/{reminderId}")
    public ResponseEntity<String> deleteReminder(@PathVariable Long reminderId) {
        try {
            reminderService.deleteReminder(reminderId);
            return ResponseEntity.ok("Reminder deleted successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

//    /**
//     * Mark a reminder as sent.
//     */
//    @PutMapping("/mark-sent/{reminderId}")
//    public ResponseEntity<Reminder> markReminderAsSent(@PathVariable Long reminderId) {
//        try {
//            Reminder updatedReminder = reminderService.markReminderAsSent(reminderId);
//            return ResponseEntity.ok(updatedReminder);
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().build();
//        }
//    }
}
