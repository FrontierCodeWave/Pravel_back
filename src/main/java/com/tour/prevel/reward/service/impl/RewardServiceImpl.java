package com.tour.prevel.reward.service.impl;

import com.tour.prevel.reward.domain.RewardType;
import com.tour.prevel.reward.dto.RewardResponse;
import com.tour.prevel.reward.repository.RewardQueryRepository;
import com.tour.prevel.reward.service.RewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RewardServiceImpl implements RewardService {

    private final RewardQueryRepository rewardQueryRepository;

    @Override
    public int getCurrentRewardCountByType(String userId, RewardType rewardType) {
        return rewardQueryRepository.getCurrentRewardCountByType(userId, rewardType);
    }

    @Override
    public List<RewardResponse> getRewardListById(String userId, boolean used) {
        return rewardQueryRepository.getRewardListById(userId, used);
    }

    @Override
    public int getTotalRewardCount(String userId) {
        return rewardQueryRepository.getTotalRewardCount(userId);
    }
}
