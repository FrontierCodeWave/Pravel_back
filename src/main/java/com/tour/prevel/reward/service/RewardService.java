package com.tour.prevel.reward.service;

import com.tour.prevel.reward.domain.RewardType;
import com.tour.prevel.reward.dto.RewardListResponse;

public interface RewardService {
    int getCurrentRewardCountByType(String userId, RewardType rewardType);
    RewardListResponse getRewardList(String userId);
    int getTotalRewardCount(String userId);
}
