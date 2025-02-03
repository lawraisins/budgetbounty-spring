package com.hcltech.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.*;

@Entity
@Table(name = "Bills")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bill_seq")
    @SequenceGenerator(name = "bill_seq", sequenceName = "bill_seq", allocationSize = 1)
    private Long billId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private LocalDateTime dueDate; //needa change to date type

    @Column(nullable = false, length = 10)
    private String recurring; // Yes / No //maybe can use boolean here?

    @Column(nullable = false, length = 10)
    private String billStatus; // Paid / Unpaid
}
