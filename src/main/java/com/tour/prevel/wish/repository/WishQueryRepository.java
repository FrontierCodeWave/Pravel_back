package com.tour.prevel.wish.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tour.prevel.tour.domain.Tour;
import com.tour.prevel.tourapi.domain.ContentCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.tour.prevel.wish.domain.QWish.wish;
import static com.tour.prevel.tour.domain.QTour.tour;

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

    public void toggleWish(ContentCategory category, String contentId, String userId) {
        if (isWished(userId, contentId)) {
            queryFactory.delete(wish)
                    .where(wish.contentId.eq(contentId)
                            .and(wish.user.email.eq(userId)))
                    .execute();
        } else {
            queryFactory.insert(wish)
                    .set(wish.contentId, contentId)
                    .set(wish.user.email, userId)
                    .set(wish.category, category)
                    .execute();
        }
    }

    public void deleteWish(String contentId, String userId) {
        queryFactory.delete(wish)
                .where(wish.contentId.eq(contentId)
                        .and(wish.user.email.eq(userId)))
                .execute();
    }

    public List<Tour> getWishList(String userId) {
         return queryFactory.select(tour)
                .from(tour, wish)
                .where(wish.user.email.eq(userId).and(tour.contentId.eq(wish.contentId)))
                .stream().toList();
    }
}
