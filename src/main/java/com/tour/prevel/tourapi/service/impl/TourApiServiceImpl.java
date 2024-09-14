package com.tour.prevel.tourapi.service.impl;

import com.tour.prevel.config.tourapi.TourApiProperties;
import com.tour.prevel.tourapi.domain.ContentTypeId;
import com.tour.prevel.tourapi.domain.TourApiUrl;
import com.tour.prevel.tourapi.dto.*;
import com.tour.prevel.tourapi.dto.params.ListParamsDto;
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
    public TourApiImageListResponse fetchImageList(TourApiUrl url, String queryParameters) {
        try {
            return webClient.get()
                    .uri(properties.getSuburl() + url.getUrl() + "?" + queryParameters)
                    .retrieve()
                    .bodyToMono(TourApiImageListResponse.class)
                    .block();
        } catch (Exception e) {
            log.error("Failed to fetch tour list from API", e);
            return TourApiImageListResponse.builder()
                    .response(TourApiImageListResponse.Response.builder()
                            .body(TourApiImageListResponse.Body.builder()
                                    .items(TourApiImageListResponse.Items.builder()
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
    public String createQueryParameters(ListParamsDto params) {
        return TourApiListRequest.builder()
                .mapX(String.valueOf(params.getMapX()))
                .mapY(String.valueOf(params.getMapY()))
                .pageNo(params.getPageNo())
                .contentTypeId(params.getContentTypeId().getId())
                .markers(params.getMarkers())
                .radius(params.getRadius())
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

    @Override
    public String createQueryParameters(String contentId, int pageNo) {
        return TourApiImageListRequest.builder()
                .contentId(contentId)
                .pageNo(pageNo)
                .serviceKey(properties.getKey())
                .build()
                .toQueryParameters();
    }
}
