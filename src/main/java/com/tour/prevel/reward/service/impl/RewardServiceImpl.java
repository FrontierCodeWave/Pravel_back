package com.tour.prevel.reward.service.impl;

import com.tour.prevel.reward.domain.RewardType;
import com.tour.prevel.reward.repository.RewardQueryRepository;
import com.tour.prevel.reward.service.RewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RewardServiceImpl implements RewardService {

    private final RewardQueryRepository rewardQueryRepository;

    @Override
    public int getCurrentRewardCountByType(String userId, RewardType rewardType) {
        return rewardQueryRepository.getCurrentRewardCountByType(userId, rewardType);
    }
}
