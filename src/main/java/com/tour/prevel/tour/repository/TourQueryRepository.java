package com.tour.prevel.tour.repository;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tour.prevel.tour.domain.Tour;
import com.tour.prevel.tourapi.domain.ContentTypeId;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.tour.prevel.tour.domain.QTour.tour;
import static com.tour.prevel.tour.domain.QTourImage.tourImage;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TourQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<Tour> findTourWithinRadius(double x, double y, double radius, Pageable pageable, ContentTypeId contentTypeId) {
        NumberExpression<Double> distance = Expressions.numberTemplate(Double.class,
                "6371000 * acos(cos(radians({0})) * cos(radians({1})) * cos(radians({2}) - radians({3})) + sin(radians({0})) * sin(radians({1})))",
                y, tour.mapY, x, tour.mapX);

        return queryFactory
                .selectFrom(tour)
                .where(distance.loe(radius)
                        .and(tour.contentTypeId.eq(contentTypeId.getId())))
                .orderBy(distance.asc()) // 거리 기준 오름차순 정렬 추가
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    public int findTourCountWithinRadius(double x, double y, double radius, ContentTypeId contentTypeId) {
        return queryFactory
                .selectFrom(tour)
                .where(Expressions.numberTemplate(Double.class,
                        "6371000 * acos(cos(radians({0})) * cos(radians({1})) * cos(radians({2}) - radians({3})) + sin(radians({0})) * sin(radians({1})))",
                            y, tour.mapY, x, tour.mapX)
                                .loe(radius).and(tour.contentTypeId.eq(contentTypeId.getId())))
                .fetch().size();
    }

    public List<Tour> findTourByKeyword(String keyword, ContentTypeId contentTypeId) {
        return queryFactory
                .selectFrom(tour)
                .where(tour.title.contains(keyword).and(tour.contentTypeId.eq(contentTypeId.getId())))
                .offset(0)
                .limit(10)
                .fetch();
    }

    public String findTourImageById(String contentId) {
        return queryFactory
                .select(tourImage.imageUrl)
                .from(tourImage)
                .where(tourImage.contentId.eq(contentId))
                .fetchFirst();
    }
}
