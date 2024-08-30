package com.tour.prevel.reward.service;

import com.tour.prevel.reward.domain.RewardType;
import com.tour.prevel.reward.dto.RewardListResponse;

public interface RewardService {
    int getCurrentRewardCountByType(String userId, RewardType rewardType);
    RewardListResponse getRewardListById(String userId, boolean used);
    int getTotalRewardCount(String userId);
}
