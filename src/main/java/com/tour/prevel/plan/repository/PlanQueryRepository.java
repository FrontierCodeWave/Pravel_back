package com.tour.prevel.plan.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tour.prevel.plan.domain.Plan;
import com.tour.prevel.plan.domain.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static com.tour.prevel.plan.domain.QSchedule.schedule;
import static com.tour.prevel.plan.domain.QPlan.plan;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlanQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<Schedule> getScheduleListByDate(String id, LocalDate date) {
        return queryFactory.selectFrom(schedule)
                .where(schedule.plan.id.eq(Long.parseLong(id))
                        .and(schedule.scheduleDate.eq(date)))
                .orderBy(schedule.scheduleOrder.desc())
                .fetch();
    }

    public List<Plan> getRecommandPlanList(String userId) {
        return queryFactory.selectFrom(plan)
                .where(plan.user.email.eq(userId))
                .fetch()
                .stream().limit(4).toList();
    }

    public List<Plan> getPlanList(String userId) {
        return queryFactory.selectFrom(plan)
                .where(plan.user.email.eq(userId))
                .fetch();
    }

    public List<Plan> getCompletedPlanList(String userId) {
        return queryFactory.selectFrom(plan)
                .where(plan.user.email.eq(userId)
                        .and(plan.endDate.before(LocalDate.now())))
                .fetch();
    }

    public int getPlanCount(String userId) {
        return (int) queryFactory.selectFrom(plan)
                .where(plan.user.email.eq(userId)
                        .and(plan.startDate.year().eq(LocalDate.now().getYear()))
                )
                .fetchCount();
    }

    public Plan getActivePlan(String userId) {
        return queryFactory.selectFrom(plan)
                .where((plan.startDate.before(LocalDate.now()).or(plan.startDate.eq(LocalDate.now())))
                        .and(plan.endDate.after(LocalDate.now()).or(plan.endDate.eq(LocalDate.now()))
                                .and(plan.user.email.eq(userId))))
                .fetchOne();
    }

    public List<Schedule> getScheduleListByPlanId(Long id) {
        return queryFactory.selectFrom(schedule)
                .where(schedule.plan.id.eq(id))
                .fetch();
    }
}
