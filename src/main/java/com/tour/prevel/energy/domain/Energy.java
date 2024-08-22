package com.tour.prevel.energy.domain;

import com.tour.prevel.auth.domain.User;
import com.tour.prevel.global.domain.CreatedTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "TB_ENERGY")
@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Energy extends CreatedTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int energy;
    private String location;
    private String title;
}
