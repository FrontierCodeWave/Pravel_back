package com.tour.prevel.tourapi.domain;

public enum TourApiUrl {
    LIST("/locationBasedList1"),
    DETAIL("/detailCommon1")
    ;

    private String url;

    TourApiUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
