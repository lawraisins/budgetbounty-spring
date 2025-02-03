package com.hcltech.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.*;

@Entity
@Table(name = "Reminders")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
}
