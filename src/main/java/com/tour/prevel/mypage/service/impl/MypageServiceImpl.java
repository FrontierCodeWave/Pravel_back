package com.tour.prevel.mypage.service.impl;

import com.tour.prevel.energy.servce.EnergyService;
import com.tour.prevel.mypage.dto.MypageTotalCountResponse;
import com.tour.prevel.mypage.service.MypageService;
import com.tour.prevel.reward.domain.RewardType;
import com.tour.prevel.reward.service.RewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MypageServiceImpl implements MypageService {

    private final EnergyService energyService;
    private final RewardService rewardService;

    @Override
    public MypageTotalCountResponse getTotalCount(String userId) {
        int currentEnergyCount = energyService.getCurrentEnergyCount(userId);
        int currentCouponCount = rewardService.getCurrentRewardCountByType(userId, RewardType.COUPON);
        int currentGiftCount = rewardService.getCurrentRewardCountByType(userId, RewardType.GIFT);
        int totalRewardCount = rewardService.getTotalRewardCount(userId);
        return MypageTotalCountResponse.builder()
                .currentEnergyCount(currentEnergyCount)
                .currentCouponCount(currentCouponCount)
                .currentGiftCount(currentGiftCount)
                .totalRewardCount(totalRewardCount)
                .build();
    }
}
