package com.tour.prevel.mypage.controller;

import com.tour.prevel.auth.utils.JwtUtil;
import com.tour.prevel.mypage.dto.MypageTotalCountResponse;
import com.tour.prevel.mypage.service.MypageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/mypage")
public class MypageController {

    private final MypageService mypageService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public MypageTotalCountResponse getTotalCount(Authentication auth) {
        return mypageService.getTotalCount(auth.getName());
    }
}
