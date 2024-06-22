package com.tour.prevel.tour.dto.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiTourResponse {

    private ApiTourListResponse.Response response;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private ApiTourResponse.Body body;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Body {
        private ApiTourResponse.Items items;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Items {

        private List<ApiTourResponse.Item> item;
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
