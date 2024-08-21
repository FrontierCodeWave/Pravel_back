package com.tour.prevel.review.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tour.prevel.review.domain.QReview;
import com.tour.prevel.review.domain.Review;
import com.tour.prevel.review.dto.ReviewListResponse;
import com.tour.prevel.review.dto.ReviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public List<Review> findByContentId(String contentId) {
        return queryFactory.selectFrom(review)
                .where(review.contentId.eq(contentId))
                .fetch();
    }

    public ReviewListResponse findAllByContentId(String contentId, Pageable pageable) {
        List<Tuple> tuples = queryFactory.select(
                        QReview.review.content,
                        QReview.review.rating,
                        QReview.review.thumnail,
                        QReview.review.id,
                        QReview.review.user.email,
                        QReview.review.user.profileImg,
                        QReview.review.reviewLike.size().as("likes")
                )
                .from(QReview.review)
                .where(QReview.review.contentId.eq(contentId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        List<ReviewResponse> list = tuples.stream().map(tuple -> ReviewResponse.builder()
                        .content(tuple.get(review.content))
                        .rating(tuple.get(review.rating))
                        .thumnail(tuple.get(review.thumnail))
                        .id(tuple.get(review.id))
                        .userId(tuple.get(review.user.email))
                        .profileImg(tuple.get(review.user.profileImg))
                        .likes(tuple.get(6, Integer.class))
                        .build())
                .toList();

        return ReviewListResponse.builder()
                .list(list)
                .total(getReviewCountById(contentId))
                .build();
    }
}
