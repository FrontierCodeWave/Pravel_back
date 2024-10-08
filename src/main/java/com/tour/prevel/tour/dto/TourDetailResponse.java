package com.tour.prevel.tour.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class TourDetailResponse implements TourCommonResponse {
    private String thumbnail;
    private String contentId;
    private String contentTypeId;
    private String title;
    private String homepage;
    private String tel;
    private String addr1;
    private String addr2;
    private String description;

    @Setter
    private String playtime;

    @Setter
    private boolean wish;
    @Setter
    private boolean like;
    @Setter
    private int review;
    @Setter
    private double rating;
}
