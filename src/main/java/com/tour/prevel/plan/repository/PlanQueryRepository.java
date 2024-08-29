package com.tour.prevel.plan.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tour.prevel.plan.domain.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static com.tour.prevel.plan.domain.QSchedule.schedule;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlanQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<Schedule> getScheduleList(String id, LocalDate date) {
        return queryFactory.selectFrom(schedule)
                .where(schedule.plan.id.eq(Long.parseLong(id))
                        .and(schedule.scheduleDate.eq(date)))
                .orderBy(schedule.scheduleOrder.desc())
                .fetch();
    }
}
