package com.tour.prevel.reward.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record RewardListResponse(List<RewardResponse> list) {
}
