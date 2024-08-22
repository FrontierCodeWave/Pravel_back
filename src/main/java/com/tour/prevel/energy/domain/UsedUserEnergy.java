package com.tour.prevel.energy.domain;

import com.tour.prevel.reward.domain.Reward;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "TB_USED_USER_ENERGY")
@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UsedUserEnergy {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEnergy userEnergy;

    private int usedEnergy;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reward reward;

    private LocalDate usedDate;
}
