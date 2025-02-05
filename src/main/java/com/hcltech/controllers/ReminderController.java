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

    // Get reminders for a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Reminder>> getRemindersForUser(@PathVariable Long userId) {
        return ResponseEntity.ok(reminderService.getRemindersForUser(userId));
    }

    // Create or update a reminder
    @PostMapping
    public ResponseEntity<Reminder> saveReminder(@RequestBody Reminder reminder) {
        return ResponseEntity.ok(reminderService.saveReminder(reminder));
    }
}
