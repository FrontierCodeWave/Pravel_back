package com.tour.prevel.mypage.service;

import com.tour.prevel.mypage.dto.MypageTotalCountResponse;

public interface MypageService {
    MypageTotalCountResponse getTotalCount(String userId);
}
