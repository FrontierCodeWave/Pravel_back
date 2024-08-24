package com.tour.prevel.tourapi.dto;

import lombok.Builder;

public class TourApiRequest extends ApiRequest {

    protected final String defaultYN = "Y";
    protected final String overviewYN = "Y";
    protected final String firstImageYN = "Y";
    protected final String addrinfoYN = "Y";
    protected final String mapinfoYN = "Y";

    protected int contentId;

    @Builder
    public TourApiRequest(String serviceKey, int contentId) {
        super(serviceKey);
        this.contentId = contentId;
    }
}
