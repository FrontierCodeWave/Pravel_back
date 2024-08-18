package com.tour.prevel.restaurant.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
public class RestaurantResponse implements RestaurantCommonResponse {

    private String contentId;
    private String thumbnail;
    private String title;
    private double lat;
    private double lon;
    private String tel;
    @Setter
    private String address;

    @Setter
    private String playtime;
    @Setter
    private List<String> treatmenu;

    @Setter
    private boolean wish;
    @Setter
    private boolean like;
    @Setter
    private int review;
    @Setter
    private double rating;
}
