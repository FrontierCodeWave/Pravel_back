package com.tour.prevel.tour.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class TourResponse {

    private String contentId;
    private String thumbnail;
    private String title;
    private double rating;
    private double lat;
    private double lon;
    private String tel;
    private String contentTypeId;
    @Setter
    private String address;

    @Setter
    private String playtime;

    @Setter
    private boolean wish;
    @Setter
    private boolean like;

}
