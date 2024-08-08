package com.tour.prevel.tourapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TourApiResponse {

    private TourApiListResponse.Response response;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private TourApiResponse.Body body;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Body {
        private TourApiResponse.Items items;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Items {

        private List<TourApiResponse.Item> item;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Item {
        private String contentid;
        private String addr1;
        private String addr2;
        private String mapx;
        private String mapy;
        private String title;
        private String tel;
        private String homepage;
        private String firstimage;
    }
}
