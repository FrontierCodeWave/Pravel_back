package com.tour.prevel.plan.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tour.prevel.plan.domain.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.tour.prevel.plan.domain.QSchedule.schedule;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<Schedule> getScheduleListByDate(String planId, LocalDate date) {
        return queryFactory.selectFrom(schedule)
                .where(schedule.plan.id.eq(Long.parseLong(planId))
                        .and(schedule.scheduleDate.eq(date)))
                .orderBy(schedule.scheduleOrder.desc())
                .fetch();
    }

    public List<Schedule> getScheduleListByPlanId(Long planId) {
        return queryFactory.selectFrom(schedule)
                .where(schedule.plan.id.eq(planId))
                .fetch();
    }

    public int getLastOrder(Long planId, String date) {
        return Optional.ofNullable(queryFactory.select(schedule.scheduleOrder.max())
                .from(schedule)
                .where(schedule.plan.id.eq(planId).and(schedule.scheduleDate.eq(LocalDate.parse(date))))
                .fetchOne()).orElse(0);
    }
}
