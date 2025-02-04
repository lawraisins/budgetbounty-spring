package com.hcltech.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Rewards")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reward_seq")
    @SequenceGenerator(name = "reward_seq", sequenceName = "reward_seq", allocationSize = 1)
    private Long rewardId;

//    @ManyToOne
//    @JoinColumn(name = "partnerId", nullable = false)
//    private Partner partner; //can exclude first

    @Column(nullable = false, length = 255)
    private String rewardName;

    @Column(nullable = false)
    private int pointsRequired;
}
