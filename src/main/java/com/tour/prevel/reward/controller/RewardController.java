package com.tour.prevel.reward.controller;

import com.tour.prevel.reward.dto.RewardListResponse;
import com.tour.prevel.reward.service.RewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reward")
public class RewardController {

    private final RewardService rewardService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public RewardListResponse getEnergyList() {
        return rewardService.getRewardList("test@test.com");
    }
}
