package com.tour.prevel.tour.service.impl;

import com.tour.prevel.config.tourapi.TourApiProperties;
import com.tour.prevel.global.exception.NotFound;
import com.tour.prevel.tour.dto.TourDetailResponse;
import com.tour.prevel.tour.dto.TourListRequest;
import com.tour.prevel.tour.dto.TourListResponse;
import com.tour.prevel.tour.dto.TourResponse;
import com.tour.prevel.tour.dto.api.ApiTourListRequest;
import com.tour.prevel.tour.dto.api.ApiTourListResponse;
import com.tour.prevel.tour.dto.api.ApiTourRequest;
import com.tour.prevel.tour.mapper.TourMapper;
import com.tour.prevel.tour.service.TourService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TourServiceImpl implements TourService {

    private final WebClient webClient;
    private final TourApiProperties properties;
    private final TourMapper tourMapper;

    @Override
    public TourListResponse getTourList(TourListRequest request) {
        String queryParameters = ApiTourListRequest.builder()
                .mapX(String.valueOf(request.x()))
                .mapY(String.valueOf(request.y()))
                .pageNo(1)
                .serviceKey(properties.getKey())
                .build()
                .toQueryParameters();

        ApiTourListResponse.Body body = fetchTourListFromAPI("/locationBasedList1", queryParameters).getResponse().getBody();
        List<TourResponse> tourResponses = tourMapper.toTourListResponse(body.getItems().getItem());
        return TourListResponse.builder()
                .list(tourResponses)
                .totalCount(body.getTotalCount())
                .build();
    }

    @Override
    public TourDetailResponse getTour(int contentId) {
        String queryParameters = ApiTourRequest.builder()
                .contentId(contentId)
                .serviceKey(properties.getKey())
                .build()
                .toQueryParameters();

        ApiTourListResponse.Body body = fetchTourListFromAPI("/detailCommon1", queryParameters).getResponse().getBody();
        List<TourDetailResponse> tourResponses = tourMapper.toTourDetailListResponse(body.getItems().getItem());
        return tourResponses.stream().findFirst()
                .orElseThrow(() -> new NotFound());
    }

    private ApiTourListResponse fetchTourListFromAPI(String url, String queryParameters) {
        try {
            return webClient.get()
                    .uri(properties.getSuburl() + url + "?" + queryParameters)
                    .retrieve()
                    .bodyToMono(ApiTourListResponse.class)
                    .block();
        } catch (Exception e) {
            log.error("Failed to fetch tour list from API", e);
            return ApiTourListResponse.builder()
                    .response(ApiTourListResponse.Response.builder()
                            .body(ApiTourListResponse.Body.builder()
                                    .items(ApiTourListResponse.Items.builder()
                                            .item(Collections.emptyList())
                                            .build()
                                    )
                                    .totalCount(0)
                                    .build())
                            .build())
                    .build();
        }
    }
}
