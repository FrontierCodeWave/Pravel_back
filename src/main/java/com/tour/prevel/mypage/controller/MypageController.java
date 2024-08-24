package com.tour.prevel.mypage.controller;

import com.tour.prevel.auth.utils.JwtUtil;
import com.tour.prevel.mypage.dto.MypageTotalCountResponse;
import com.tour.prevel.mypage.service.MypageService;
import io.swagger.v3.oas.annotations.headers.Header;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/mypage")
public class MypageController {

    private final MypageService mypageService;
    private final JwtUtil jwtUtil;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public MypageTotalCountResponse getTotalCount(
//            @RequestHeader("Authorization") String token
    ) {
//        String userId = jwtUtil.getUserId(token);
        return mypageService.getTotalCount("test@test.com");
    }
}