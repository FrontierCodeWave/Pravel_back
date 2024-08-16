package com.tour.prevel.rating.domain;

import com.tour.prevel.auth.domain.User;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Table(name = "TB_RATING")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class StarRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;
    private String contentId;
    private double rating;
}
