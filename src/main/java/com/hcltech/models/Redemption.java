package com.hcltech.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.*;

@Entity
@Table(name = "Redemptions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Redemption {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "redemption_seq")
    @SequenceGenerator(name = "redemption_seq", sequenceName = "redemption_seq", allocationSize = 1)
    private Long redemptionId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

//    @ManyToOne
//    @JoinColumn(name = "adminId", nullable = false)
//    private Admin admin; //can exclude first

    @ManyToOne
    @JoinColumn(name = "rewardId", nullable = false)
    private Reward reward;

    @Column(nullable = false)
    private LocalDateTime redemptionDate;

    @Column(nullable = false)
    private LocalDateTime expiryDate;

    @Column(nullable = false, length = 255)
    private String status; // Pending, Approved, Rejected
}
