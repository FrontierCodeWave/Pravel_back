package com.tour.prevel.restaurant.service.impl;

import com.tour.prevel.global.exception.NotFound;
import com.tour.prevel.restaurant.dto.RestaurantDetailResponse;
import com.tour.prevel.restaurant.dto.RestaurantListRequest;
import com.tour.prevel.restaurant.dto.RestaurantListResponse;
import com.tour.prevel.restaurant.dto.RestaurantResponse;
import com.tour.prevel.restaurant.mapper.RestaurantMapper;
import com.tour.prevel.restaurant.service.RestaurantService;
import com.tour.prevel.tourapi.domain.ContentTypeId;
import com.tour.prevel.tourapi.domain.TourApiUrl;
import com.tour.prevel.tourapi.dto.TourApiListResponse;
import com.tour.prevel.tourapi.service.TourApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantMapper restaurantMapper;
    private final TourApiService tourApiService;

    @Override
    public RestaurantListResponse getRestaurantList(RestaurantListRequest request) {
        String queryParameters = tourApiService.createQueryParameters(request.x(), request.y(), request.pageNo(), ContentTypeId.RESTAURANT);

        TourApiListResponse.Body body = tourApiService.fetchList(TourApiUrl.LIST, queryParameters).getResponse().getBody();
        List<RestaurantResponse> restaurantResponses = restaurantMapper.toRestaurantListResponse(body.getItems().getItem());
        return RestaurantListResponse.builder()
                .list(restaurantResponses)
                .totalCount(body.getTotalCount())
                .build();
    }

    @Override
    public RestaurantDetailResponse getRestaurant(int contentId) {
        String queryParameters = tourApiService.createQueryParameters(contentId);

        TourApiListResponse.Body body = tourApiService.fetchList(TourApiUrl.DETAIL, queryParameters)
                .getResponse().getBody();
        List<RestaurantDetailResponse> restaurantResponses = restaurantMapper.toRestaurantDetailListResponse(body.getItems().getItem());
        return restaurantResponses.stream().findFirst()
                .orElseThrow(() -> new NotFound());
    }
}
