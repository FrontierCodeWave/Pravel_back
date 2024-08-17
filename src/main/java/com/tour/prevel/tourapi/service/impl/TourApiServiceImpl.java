package com.tour.prevel.tourapi.service.impl;

import com.tour.prevel.config.tourapi.TourApiProperties;
import com.tour.prevel.tourapi.domain.ContentTypeId;
import com.tour.prevel.tourapi.domain.TourApiUrl;
import com.tour.prevel.tourapi.dto.*;
import com.tour.prevel.tourapi.service.TourApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class TourApiServiceImpl implements TourApiService {

    private final WebClient webClient;
    private final TourApiProperties properties;

    @Override
    public TourApiListResponse fetchList(TourApiUrl url, String queryParameters) {
        try {
            return webClient.get()
                    .uri(properties.getSuburl() + url.getUrl() + "?" + queryParameters)
                    .retrieve()
                    .bodyToMono(TourApiListResponse.class)
                    .block();
        } catch (Exception e) {
            log.error("Failed to fetch tour list from API", e);
            return TourApiListResponse.builder()
                    .response(TourApiListResponse.Response.builder()
                            .body(TourApiListResponse.Body.builder()
                                    .items(TourApiListResponse.Items.builder()
                                            .item(Collections.emptyList())
                                            .build()
                                    )
                                    .totalCount(0)
                                    .build())
                            .build())
                    .build();
        }
    }

    @Override
    public TourApiDetailIntroResponse fetchDetailIntro(TourApiUrl url, String queryParameters) {
        try {
            return webClient.get()
                    .uri(properties.getSuburl() + url.getUrl() + "?" + queryParameters)
                    .retrieve()
                    .bodyToMono(TourApiDetailIntroResponse.class)
                    .block();
        } catch (Exception e) {
            log.error("Failed to fetch tour list from API", e);
            return TourApiDetailIntroResponse.builder()
                    .response(TourApiDetailIntroResponse.Response.builder()
                            .body(TourApiDetailIntroResponse.Body.builder()
                                    .items(TourApiDetailIntroResponse.Items.builder()
                                            .item(Collections.emptyList())
                                            .build()
                                    )
                                    .totalCount(0)
                                    .build())
                            .build())
                    .build();
        }
    }

    @Override
    public String createQueryParameters(double mapX, double mapY, Integer pageNo, ContentTypeId contentTypeId) {
        return TourApiListRequest.builder()
                .mapX(String.valueOf(mapX))
                .mapY(String.valueOf(mapY))
                .pageNo(pageNo == null ? 1 : pageNo)
                .contentTypeId(contentTypeId.getId())
                .serviceKey(properties.getKey())
                .build()
                .toQueryParameters();
    }

    @Override
    public String createQueryParameters(int contentId) {
        return TourApiRequest.builder()
                .contentId(contentId)
                .serviceKey(properties.getKey())
                .build()
                .toQueryParameters();
    }

    @Override
    public String createQueryParameters(String contentId, String contentTypeId) {
        return TourApiDetailIntroRequest.builder()
                .contentId(contentId)
                .contentTypeId(contentTypeId)
                .serviceKey(properties.getKey())
                .build()
                .toQueryParameters();
    }

    @Override
    public String createQueryParameters(String search, ContentTypeId contentTypeId) {
        return TourApiSearchListRequest.builder()
                .contentTypeId(contentTypeId.getId())
                .keyword(search)
                .serviceKey(properties.getKey())
                .build()
                .toQueryParameters();
    }

}
