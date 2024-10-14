package com.tour.prevel.plan.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "TB_SCHEDULE")
@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ScheduleCategory category;
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;
    private int scheduleOrder;
    private LocalDate scheduleDate;

    @Column(columnDefinition = "TEXT")
    private String thumbnail;

    @ManyToOne(fetch = FetchType.LAZY)
    private Plan plan;
}
