package com.tour.prevel.tour.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TourDetailResponse {

    private String firstImage;
    private String contentId;
    private String title;
    private String addr1;
    private String addr2;
    private String homepage;
    private String tel;
    private double mapx;
    private double mapy;
    private boolean like;
}
