package com.tour.prevel.tourapi.dto;

import lombok.Builder;

public class TourApiImageListRequest extends ApiRequest {

    protected final String imageYN = "Y";
    protected final String subImageYN = "Y";
    protected String contentId;
    protected int pageNo;

    @Builder
    public TourApiImageListRequest(String serviceKey, String contentId, int pageNo) {
        super(serviceKey);
        this.contentId = contentId;
        this.pageNo = pageNo;
    }
}
