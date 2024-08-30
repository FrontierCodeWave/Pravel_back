package com.tour.prevel.reward.controller;

import com.tour.prevel.auth.utils.JwtUtil;
import com.tour.prevel.reward.dto.RewardListResponse;
import com.tour.prevel.reward.service.RewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reward")
public class RewardController {

    private final RewardService rewardService;
    private final JwtUtil jwtUtil;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public RewardListResponse getMyRewardList(
            @RequestHeader(name = "Authorization") String token,
            boolean used
    ) {
        String userId = jwtUtil.getUserId(token);
        return rewardService.getRewardListById(userId, used);
    }
}
