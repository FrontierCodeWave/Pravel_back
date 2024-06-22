package com.tour.prevel.tour.dto.api;

import lombok.Builder;

public class ApiTourListRequest extends ApiRequest {
    protected final String radius = "1000";

    protected String mapX;
    protected String mapY;
    protected int pageNo;

    @Builder
    public ApiTourListRequest(String serviceKey, String mapX, String mapY, int pageNo) {
        super(serviceKey);
        this.mapX = mapX;
        this.mapY = mapY;
        this.pageNo = pageNo;
    }
}
