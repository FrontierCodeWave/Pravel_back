package com.tour.prevel.reward.service;

import com.tour.prevel.reward.domain.RewardType;

public interface RewardService {
    int getCurrentRewardCountByType(String userId, RewardType rewardType);
}
