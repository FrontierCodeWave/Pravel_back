package com.tour.prevel.tourapi.dto;

import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TourApiListResponse {

    public TourApiListResponse.Response response;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {

        private TourApiListResponse.Body body;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Body {

        private TourApiListResponse.Items items;
        private int totalCount;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Items {

        private List<TourApiListResponse.Item> item;
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
        private String overview;
        private String firstimage;
        private String contenttypeid;
    }
}
