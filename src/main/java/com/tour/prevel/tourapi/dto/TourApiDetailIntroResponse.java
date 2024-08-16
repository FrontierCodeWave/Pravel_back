package com.tour.prevel.tourapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TourApiDetailIntroResponse {

    public TourApiDetailIntroResponse.Response response;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {

        private TourApiDetailIntroResponse.Body body;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Body {

        private TourApiDetailIntroResponse.Items items;
        private int totalCount;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Items {

        private List<TourApiDetailIntroResponse.Item> item;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Item {

        private String contentid;
        private String contenttypeid;

        private String usetime;
        private String usetimeculture;
        private String playtime;
        private String taketime;
        private String usetimeleports;
        private String opentime;

        private String treatmenu;
    }
}
