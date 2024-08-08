package com.tour.prevel.tourapi.dto;

import lombok.Builder;

public class TourApiListRequest extends ApiRequest {
    protected final String radius = "1000";

    protected String mapX;
    protected String mapY;
    protected int pageNo;
    protected String contentTypeId;

    @Builder
    public TourApiListRequest(String serviceKey, String mapX, String mapY, int pageNo, String contentTypeId) {
        super(serviceKey);
        this.mapX = mapX;
        this.mapY = mapY;
        this.pageNo = pageNo;
        this.contentTypeId = contentTypeId;
    }
}
