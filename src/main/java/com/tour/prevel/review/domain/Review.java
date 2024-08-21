package com.tour.prevel.review.domain;

import com.tour.prevel.auth.domain.User;
import com.tour.prevel.global.domain.CreatedTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Entity
@Table(name = "TB_REVIEW")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
public class Review extends CreatedTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contentId; // 관광 또는 맛집 아이디

    private String content;
    private double rating;
    private String thumnail;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "review")
    private List<ReviewLike> reviewLike;
}
