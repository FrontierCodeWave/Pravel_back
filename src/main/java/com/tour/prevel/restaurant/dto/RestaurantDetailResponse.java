package com.tour.prevel.restaurant.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
public class RestaurantDetailResponse implements RestaurantCommonResponse {

    private String thumbnail;
    private String contentId;
    private String title;
    private String homepage;
    private String tel;
    private String addr1;
    private String addr2;

    @Setter
    private String playtime;
    @Setter
    private List<String> hashtags;

    @Setter
    private boolean wish;
    @Setter
    private boolean like;
    @Setter
    private int review;
    @Setter
    private double rating;
}