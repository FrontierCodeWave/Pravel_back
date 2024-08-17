package com.tour.prevel.tourapi.dto;

import lombok.Builder;

public class TourApiSearchListRequest extends ApiRequest {

    protected String keyword;
    protected String contentTypeId;

    @Builder
    public TourApiSearchListRequest(String serviceKey, String keyword, String contentTypeId) {
        super(serviceKey);
        this.keyword = keyword;
        this.contentTypeId = contentTypeId;
    }
}
