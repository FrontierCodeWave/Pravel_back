package com.tour.prevel.config.tourapi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties("tour.api")
@AllArgsConstructor
public class TourApiProperties {

    private String key;
    private String url;
    private String suburl;
}
