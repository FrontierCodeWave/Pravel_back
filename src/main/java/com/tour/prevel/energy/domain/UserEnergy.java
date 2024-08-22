package com.tour.prevel.energy.domain;

import com.tour.prevel.auth.domain.User;
import com.tour.prevel.global.domain.CreatedTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Table(name = "TB_USER_ENERGY")
@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserEnergy extends CreatedTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_energy_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "energy_id")
    private Energy energy;

    private LocalDate expirationDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userEnergy")
    private List<UsedUserEnergy> usedUserEnergyList;
}
