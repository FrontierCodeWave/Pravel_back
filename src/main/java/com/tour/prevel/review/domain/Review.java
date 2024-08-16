package com.tour.prevel.review.domain;

import com.tour.prevel.auth.domain.User;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Table(name = "TB_REVIEW")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String contentId;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;
}
