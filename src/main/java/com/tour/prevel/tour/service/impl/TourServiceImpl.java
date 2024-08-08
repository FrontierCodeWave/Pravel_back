package com.tour.prevel.tour.service.impl;

import com.tour.prevel.config.tourapi.TourApiProperties;
import com.tour.prevel.global.exception.NotFound;
import com.tour.prevel.tour.dto.TourDetailResponse;
import com.tour.prevel.tour.dto.TourListRequest;
import com.tour.prevel.tour.dto.TourListResponse;
import com.tour.prevel.tour.dto.TourResponse;
import com.tour.prevel.tourapi.domain.ContentTypeId;
import com.tour.prevel.tourapi.domain.TourApiUrl;
import com.tour.prevel.tourapi.service.TourApiService;
import com.tour.prevel.tourapi.dto.TourApiListResponse;
import com.tour.prevel.tour.mapper.TourMapper;
import com.tour.prevel.tour.service.TourService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TourServiceImpl implements TourService {

    private final TourMapper tourMapper;
    private final TourApiService tourApiService;

    @Override
    public TourListResponse getTourList(TourListRequest request) {
        String queryParameters = tourApiService.createQueryParameters(request.x(), request.y(), request.pageNo(), ContentTypeId.TOUR);

        TourApiListResponse.Body body = tourApiService.fetchListFromTourAPI(TourApiUrl.LIST, queryParameters).getResponse().getBody();
        List<TourResponse> tourResponses = tourMapper.toTourListResponse(body.getItems().getItem());
        return TourListResponse.builder()
                .list(tourResponses)
                .totalCount(body.getTotalCount())
                .build();
    }

    @Override
    public TourDetailResponse getTour(int contentId) {
        String queryParameters = tourApiService.createQueryParameters(contentId);

        TourApiListResponse.Body body = tourApiService.fetchListFromTourAPI(TourApiUrl.DETAIL, queryParameters)
                .getResponse().getBody();
        List<TourDetailResponse> tourResponses = tourMapper.toTourDetailListResponse(body.getItems().getItem());
        return tourResponses.stream().findFirst()
                .orElseThrow(() -> new NotFound());
    }
}
