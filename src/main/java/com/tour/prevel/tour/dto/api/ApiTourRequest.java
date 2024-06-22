package com.tour.prevel.tour.dto.api;

import lombok.Builder;

public class ApiTourRequest extends ApiRequest {

    protected final String defaultYN = "Y";
    protected final String firstImageYN = "Y";
    protected final String addrinfoYN = "Y";
    protected final String mapinfoYN = "Y";

    protected int contentId;

    @Builder
    public ApiTourRequest(String serviceKey, int contentId) {
        super(serviceKey);
        this.contentId = contentId;
    }
}
