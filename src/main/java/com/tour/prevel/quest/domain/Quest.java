package com.tour.prevel.quest.domain;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "TB_QUEST")
@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Quest {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double x;
    private double y;

    private int energy;
    private String title;
}
