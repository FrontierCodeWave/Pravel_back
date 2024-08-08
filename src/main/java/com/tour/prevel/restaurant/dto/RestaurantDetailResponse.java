package com.tour.prevel.restaurant.dto;

import lombok.Builder;

@Builder
public record RestaurantDetailResponse(
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
