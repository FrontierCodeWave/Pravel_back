package com.tour.prevel.reward.controller;

import com.tour.prevel.reward.dto.RewardListResponse;
import com.tour.prevel.reward.service.RewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reward")
public class RewardController {

    private final RewardService rewardService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public RewardListResponse getMyRewardList(
            Authentication auth,
            boolean used
    ) {
        return rewardService.getRewardListById(auth.getName(), used);
    }
}
