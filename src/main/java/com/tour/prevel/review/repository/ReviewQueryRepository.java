package com.tour.prevel.review.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static com.tour.prevel.review.domain.QReview.review;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryRepository {

    private final JPAQueryFactory queryFactory;

    public int getReviewCountById(String contentId) {
        return (int) queryFactory.selectFrom(review)
                .where(review.contentId.eq(contentId))
                .fetchCount();
    }
}
