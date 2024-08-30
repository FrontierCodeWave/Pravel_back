package com.tour.prevel.reward.service;

import com.tour.prevel.reward.domain.RewardType;
import com.tour.prevel.reward.dto.RewardResponse;

import java.util.List;

public interface RewardService {
    int getCurrentRewardCountByType(String userId, RewardType rewardType);
    List<RewardResponse> getRewardListById(String userId, boolean used);
    int getTotalRewardCount(String userId);
}
