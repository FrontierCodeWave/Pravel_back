package com.tour.prevel.tour.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class TourResponse implements TourCommonResponse {

    private String contentId;
    private String thumbnail;
    private String title;
    private double lat;
    private double lon;
    private String tel;
    private String contentTypeId;
    @Setter
    private String address;
    @Setter
    private String category;

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
