package com.hcltech.models;

import jakarta.persistence.*;
import java.time.*;

@Entity
@Table(name = "Reminders")
public class Reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reminder_seq")
    @SequenceGenerator(name = "reminder_seq", sequenceName = "reminder_seq", allocationSize = 1)
    private Long reminderId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "billId", nullable = false)
    private Bill bill;

    @Column(nullable = false)
    private LocalDateTime reminderDateTime; //also might need date type here

    @Column(nullable = false, length = 20)
    private String notificationStatus; // Sent / Pending
    
 // Default constructor
    public Reminder() {}

    // Parameterized constructor
    public Reminder(User user, Bill bill, LocalDateTime reminderDateTime, String notificationStatus) {
        this.user = user;
        this.bill = bill;
        this.reminderDateTime = reminderDateTime;
        this.notificationStatus = notificationStatus;
    }

    // Getters and Setters
    public Long getReminderId() {
        return reminderId;
    }

    public void setReminderId(Long reminderId) {
        this.reminderId = reminderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public LocalDateTime getReminderDateTime() {
        return reminderDateTime;
    }

    public void setReminderDateTime(LocalDateTime reminderDateTime) {
        this.reminderDateTime = reminderDateTime;
    }

    public String getNotificationStatus() {
        return notificationStatus;
    }

    public void setNotificationStatus(String notificationStatus) {
        this.notificationStatus = notificationStatus;
    }
}
