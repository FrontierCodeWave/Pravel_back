package com.tour.prevel.tourapi.dto;

import lombok.Builder;

import java.util.Optional;

public class TourApiListRequest extends ApiRequest {
    protected String radius = "1000";
    protected String numOfRows = "10";

    protected String mapX;
    protected String mapY;
    protected int pageNo;
    protected String contentTypeId;

    @Builder
    public TourApiListRequest(String serviceKey, String mapX, String mapY, Integer pageNo, String contentTypeId, Integer radius, Integer markers) {
        super(serviceKey);
        this.mapX = mapX;
        this.mapY = mapY;
        this.pageNo = Optional.ofNullable(pageNo).orElse(1);
        this.contentTypeId = contentTypeId;
        this.radius = String.valueOf(Optional.ofNullable(radius).orElse(1000));
        this.numOfRows = String.valueOf(Optional.ofNullable(markers).orElse(10));
    }
}
