package com.tour.prevel.plan.domain;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "TB_PLAN_IMAGE")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PlanImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;
}
