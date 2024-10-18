package com.tour.prevel.restaurant.service.impl;

import com.tour.prevel.global.exception.NotFound;
import com.tour.prevel.restaurant.dto.*;
import com.tour.prevel.restaurant.mapper.RestaurantMapper;
import com.tour.prevel.restaurant.service.RestaurantService;
import com.tour.prevel.review.service.ReviewService;
import com.tour.prevel.tour.domain.Tour;
import com.tour.prevel.tour.repository.TourImageRepository;
import com.tour.prevel.tour.repository.TourQueryRepository;
import com.tour.prevel.tour.repository.TourRepository;
import com.tour.prevel.tourapi.domain.ContentTypeId;
import com.tour.prevel.tourapi.service.TourApiService;
import com.tour.prevel.wish.service.WishService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantMapper restaurantMapper;
    private final TourApiService tourApiService;
    private final ReviewService reviewService;
    private final WishService wishService;

    private final TourRepository tourRepository;
    private final TourImageRepository tourImageRepository;

    private final TourQueryRepository tourQueryRepository;

    @Override
    public RestaurantListResponse getRestaurantList(RestaurantListRequest request, Pageable pageNo, String userId) {
        List<Tour> tours = tourQueryRepository.findTourWithinRadius(
                request.getX(), request.getY(), request.getRadius(), pageNo, ContentTypeId.RESTAURANT);

        List<RestaurantResponse> restaurantResponses = restaurantMapper.toRestaurantListResponses(tours);
        List<RestaurantResponse> list = restaurantResponses.stream()
                .map((response) -> addInform(response, userId))
                .toList();

        return RestaurantListResponse.builder()
                .list(list)
                .totalCount(tourQueryRepository.findTourCountWithinRadius(
                        request.getX(), request.getY(), request.getRadius(), ContentTypeId.RESTAURANT))
                .build();
    }

    private <T extends RestaurantCommonResponse> T addInform(T restaurantResponse, String userId) {
        // 평점
        double rating = reviewService.getRatingById(restaurantResponse.getContentId());
        restaurantResponse.setRating(rating);

        // 리뷰수
        int review = reviewService.getReviewCountById(restaurantResponse.getContentId());
        restaurantResponse.setReview(review);

        // 위시리스트 여부
        boolean wished = wishService.isWished(userId, restaurantResponse.getContentId());
        restaurantResponse.setWish(wished);

        return restaurantResponse;
    }

    @Override
    public RestaurantDetailResponse getRestaurant(int contentId, String userId) {
        RestaurantDetailResponse restaurantDetailResponse = restaurantMapper.toRestaurantDetailResponse(
                tourRepository.findByContentId(String.valueOf(contentId)).orElseThrow(() -> new NotFound())
        );

        return addInform(restaurantDetailResponse, userId);
    }
}
