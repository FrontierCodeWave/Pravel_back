package com.tour.prevel.tour.dto;

import lombok.Builder;

@Builder
public record TourDetailResponse(
        String firstImage,
        String contentId,
        String title,
        String addr1,
        String addr2,
        String homepage,
        String tel,
        double mapx,
        double mapy,
        boolean like
) {}
