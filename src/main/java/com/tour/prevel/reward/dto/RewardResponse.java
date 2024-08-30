package com.tour.prevel.reward.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record RewardResponse(
        Long id,
        String name,
        boolean used,
        LocalDate expirationDate,
        LocalDate usedDate
) {
}
