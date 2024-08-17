package com.tour.prevel.rating.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tour.prevel.rating.domain.StarRating;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.tour.prevel.rating.domain.QStarRating.starRating;

@RequiredArgsConstructor
@Repository
@Transactional(readOnly = true)
public class StarRatingQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<StarRating> findByContentId(String contentId) {
        return queryFactory.selectFrom(starRating)
                .where(starRating.contentId.eq(contentId))
                .fetch();
    }
}
