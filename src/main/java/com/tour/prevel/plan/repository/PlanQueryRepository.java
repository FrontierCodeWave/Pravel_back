package com.tour.prevel.plan.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tour.prevel.plan.domain.Plan;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static com.tour.prevel.plan.domain.QPlan.plan;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlanQueryRepository {

    private final JPAQueryFactory queryFactory;

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
                .where(plan.user.email.eq(userId)
                        .and(plan.endDate.goe(LocalDate.now())))
                .orderBy(plan.endDate.desc())
                .fetchFirst();
    }

}
