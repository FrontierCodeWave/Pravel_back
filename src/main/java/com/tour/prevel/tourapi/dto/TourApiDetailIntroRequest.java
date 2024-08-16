package com.tour.prevel.tourapi.dto;

import lombok.Builder;

public class TourApiDetailIntroRequest extends ApiRequest {

    protected String contentId;
    protected String contentTypeId;

    @Builder
    public TourApiDetailIntroRequest(String serviceKey, String contentId, String contentTypeId) {
        super(serviceKey);
        this.contentId = contentId;
        this.contentTypeId = contentTypeId;
    }
}
