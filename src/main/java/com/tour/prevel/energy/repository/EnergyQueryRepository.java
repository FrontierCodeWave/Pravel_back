package com.tour.prevel.energy.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tour.prevel.energy.domain.Energy;
import com.tour.prevel.energy.domain.UserEnergy;
import com.tour.prevel.energy.dto.EnergyResponse;
import com.tour.prevel.energy.dto.UsedEnergyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static com.tour.prevel.energy.domain.QEnergy.energy1;
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

    public List<EnergyResponse> getEnergyListById(String userId, boolean used) {
        Map<Long, UsedEnergyResponse> usedEnergyResponseMap = queryFactory
                .select(
                        usedUserEnergy.userEnergy,
                        usedUserEnergy.usedEnergy.sum().as("usedEnergy")
                )
                .from(usedUserEnergy)
                .where(usedUserEnergy.userEnergy.user.email.eq(userId))
                .groupBy(usedUserEnergy.userEnergy)
                .fetch()
                .stream()
                .collect(
                        Collectors.toMap(
                                energy -> energy.get(usedUserEnergy.userEnergy).getId(),
                                energy -> UsedEnergyResponse.builder()
                                        .usedEnergy(energy.get(1, Integer.class).intValue())
                                        .usedDate(energy.get(0, UserEnergy.class).getUsedUserEnergyList().get(0).getUsedDate())
                                        .build())

                );

        List<Tuple> gainEnergyList = queryFactory
                .select(userEnergy, energy1)
                .from(userEnergy)
                .innerJoin(userEnergy.energy, energy1)
                .where(userEnergy.user.email.eq(userId))
                .fetch();

        List<EnergyResponse> list = gainEnergyList.stream()
                .filter((energy) -> {
                    UserEnergy gainUserEnergy = energy.get(userEnergy);
                    Energy gainEnergy = energy.get(energy1);
                    UsedEnergyResponse usedEnergyResponse = usedEnergyResponseMap.get(gainUserEnergy.getId());
                    int restEnergy = gainEnergy.getEnergy() - usedEnergyResponse.usedEnergy();
                    return used ? restEnergy == 0 : restEnergy > 0;
                }).map((energy) -> {
                    UserEnergy gainUserEnergy = energy.get(userEnergy);
                    Energy gainEnergy = energy.get(energy1);
                    UsedEnergyResponse usedEnergyResponse = usedEnergyResponseMap.get(gainUserEnergy.getId());

                    return EnergyResponse.builder()
                            .id(gainUserEnergy.getId())
                            .title(gainEnergy.getTitle())
                            .location(gainEnergy.getLocation())
                            .expirationDate(gainUserEnergy.getExpirationDate())
                            .usedDate(usedEnergyResponse.usedDate())
                            .used(gainEnergy.getEnergy() - usedEnergyResponse.usedEnergy() == 0)
                            .energy(gainEnergy.getEnergy())
                            .build();
                }).toList();

        return list;
    }
}
