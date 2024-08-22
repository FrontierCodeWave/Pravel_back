package com.tour.prevel.reward.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tour.prevel.reward.domain.RewardType;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;
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
}
