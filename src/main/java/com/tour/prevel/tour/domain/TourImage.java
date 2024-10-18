package com.tour.prevel.tour.domain;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "TB_TOUR_IMAGE")
@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class TourImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contentId;

    @Column(columnDefinition = "TEXT")
    private String imageUrl;
}
