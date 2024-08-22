package com.tour.prevel.reward.domain;

import com.tour.prevel.auth.domain.User;
import com.tour.prevel.global.domain.CreatedTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "TB_USER_REWARD")
@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserReward extends CreatedTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    private Reward reward;

    private LocalDate expirationDate;
    private LocalDate usedDate;
    private boolean used;
}
