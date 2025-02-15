package com.tour.prevel.plan.repository;

import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tour.prevel.plan.domain.Plan;
import com.tour.prevel.plan.domain.PlanStatus;
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
                .orderBy(
                        new CaseBuilder()
                                .when(plan.status.eq(PlanStatus.PROGRESS)).then(1)
                                .otherwise(2).asc(),
                        // endDate가 가까운 순으로 정렬
                        plan.endDate.asc()
                )
                .fetchFirst();
    }

    public List<Plan> getFuturePlan(String userId) {
        return queryFactory.selectFrom(plan)
                .where(plan.user.email.eq(userId)
                        .and(plan.endDate.goe(LocalDate.now())))
                .orderBy(plan.startDate.asc(), plan.id.asc())
                .fetch();
    }

    @Transactional
    public void deactivatePlan(String userId, Long id) {
        queryFactory.update(plan)
                .set(plan.status, PlanStatus.REVISION)
                .where(plan.user.email.eq(userId)
                        .and(plan.status.eq(PlanStatus.PROGRESS))
                        .and(plan.id.ne(id)))
                .execute();
    }
}
