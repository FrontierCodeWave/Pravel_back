package com.tour.prevel.restaurant.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RestaurantResponse {

    private String contentId;
    private String firstImage;
    private String title;
    private double mapx;
    private double mapy;
    private boolean like;
}
