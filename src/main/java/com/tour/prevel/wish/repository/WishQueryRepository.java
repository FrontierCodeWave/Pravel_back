package com.tour.prevel.wish.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static com.tour.prevel.wish.domain.QWish.wish;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WishQueryRepository {

    private final JPAQueryFactory queryFactory;

    public boolean isWished(String email, String contentId) {
        return queryFactory.selectFrom(wish)
                .where(wish.contentId.eq(contentId)
                        .and(wish.user.email.eq(email)))
                .fetchCount() > 0;
    }
}
