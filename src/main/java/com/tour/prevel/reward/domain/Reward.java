package com.tour.prevel.reward.domain;

import com.tour.prevel.global.domain.CreatedTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "TB_REWARD")
@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Reward extends CreatedTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDate expirationDate;

    @Enumerated(EnumType.STRING)
    private RewardType rewardType;
}
