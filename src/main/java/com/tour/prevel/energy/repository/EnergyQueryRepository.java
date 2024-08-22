package com.tour.prevel.energy.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.tour.prevel.energy.domain.QUserEnergy.userEnergy;
import static com.tour.prevel.energy.domain.QUsedUserEnergy.usedUserEnergy;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EnergyQueryRepository {

    private final JPAQueryFactory queryFactory;

    public int getCurrentEnergyCount(String userId) {
        int gainedEnergy = Optional.ofNullable(queryFactory.select(userEnergy.energy.energy.sum())
                .from(userEnergy)
                .where(userEnergy.user.email.eq(userId))
                .fetchOne()).orElse(0);

        int usedEnergy = Optional.ofNullable(queryFactory.select(usedUserEnergy.usedEnergy.sum())
                .from(usedUserEnergy)
                .where(usedUserEnergy.userEnergy.user.email.eq(userId))
                .fetchOne()).orElse(0);
        return gainedEnergy - usedEnergy;
    }
}
