package com.tour.prevel.mypage.dto;

import lombok.Builder;

@Builder
public record MypageTotalCountResponse(
        int currentEnergyCount,
        int currentCouponCount,
        int currentGiftCount
) {
}
