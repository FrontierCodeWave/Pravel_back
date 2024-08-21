package com.tour.prevel.review.dto;

import lombok.Builder;

@Builder
public record ReviewResponse(
        Long id,
        String userId,
        String profileImg,
        String content,
        double rating,
        String thumnail,
        int likes
) {}
