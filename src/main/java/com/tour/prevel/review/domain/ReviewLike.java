package com.tour.prevel.review.domain;

import com.tour.prevel.auth.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Builder
@Entity
@Table(name = "TB_REVIEW_LIKE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@IdClass(ReviewLike.ReviewUserId.class)
public class ReviewLike {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    private Review review;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @EqualsAndHashCode
    @NoArgsConstructor
    public static class ReviewUserId implements Serializable {
        private Review review;
        private User user;
    }
}
