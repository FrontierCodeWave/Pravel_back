package com.tour.prevel.reward.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tour.prevel.reward.domain.RewardType;
import com.tour.prevel.reward.dto.RewardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.tour.prevel.reward.domain.QUserReward.userReward;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RewardQueryRepository {

    private final JPAQueryFactory queryFactory;

    public int getCurrentRewardCountByType(String userId, RewardType type) {
        return Optional.ofNullable(queryFactory
                .select(userReward.used.eq(false).count())
                .from(userReward)
                .where(
                        userReward.user.email.eq(userId)
                                .and(userReward.used.eq(false))
                                .and(userReward.reward.rewardType.eq(type)))
                .fetchOne()).orElse(0L).intValue();
    }

    public List<RewardResponse> getRewardListById(String userId, boolean used) {
        List<Tuple> fetch = queryFactory
                .select(
                        userReward.id,
                        userReward.reward.name,
                        userReward.used,
                        userReward.reward.expirationDate,
                        userReward.usedDate
                )
                .from(userReward)
                .innerJoin(userReward.user)
                .where(userReward.user.email.eq(userId))
                .fetch();

        List<RewardResponse> list = fetch.stream()
                .filter(tuple -> used == (boolean) tuple.get(userReward.used))
                .map(tuple -> RewardResponse.builder()
                        .id(tuple.get(userReward.id))
                        .name(tuple.get(userReward.reward.name))
                        .used(tuple.get(userReward.used))
                        .expirationDate(tuple.get(userReward.reward.expirationDate))
                        .usedDate(tuple.get(userReward.usedDate))
                        .build())
                .toList();

        return list;
    }

    public int getTotalRewardCount(String userId) {
        return Optional.ofNullable(queryFactory
                .selectFrom(userReward)
                .where(userReward.user.email.eq(userId))
                .fetchCount()).orElse(0L).intValue();
    }
}
