package com.tour.prevel.tourapi.domain;

public enum TourApiUrl {
    LIST("/locationBasedList1"),
    SEARCH_LIST("/searchKeyword1"),
    DETAIL("/detailCommon1"),
    DETAIL_INTRO("/detailIntro1"),
    IMAGE_LIST("/detailImage1")
    ;

    private String url;

    TourApiUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
