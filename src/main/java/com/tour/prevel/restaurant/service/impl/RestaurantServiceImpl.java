package com.tour.prevel.restaurant.service.impl;

import com.tour.prevel.global.exception.NotFound;
import com.tour.prevel.restaurant.domain.FoodCategory;
import com.tour.prevel.restaurant.dto.*;
import com.tour.prevel.restaurant.mapper.RestaurantMapper;
import com.tour.prevel.restaurant.service.RestaurantService;
import com.tour.prevel.review.service.ReviewService;
import com.tour.prevel.tourapi.domain.ContentTypeId;
import com.tour.prevel.tourapi.domain.TourApiUrl;
import com.tour.prevel.tourapi.dto.TourApiDetailIntroResponse;
import com.tour.prevel.tourapi.dto.TourApiListResponse;
import com.tour.prevel.tourapi.service.TourApiService;
import com.tour.prevel.wish.service.WishService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantMapper restaurantMapper;
    private final TourApiService tourApiService;
    private final ReviewService reviewService;
    private final WishService wishService;

    @Override
    public RestaurantListResponse getRestaurantList(RestaurantListRequest request) {
        String queryParameters = tourApiService.createQueryParameters(request.x(), request.y(), request.pageNo(), ContentTypeId.RESTAURANT);

        TourApiListResponse.Body body = tourApiService.fetchList(TourApiUrl.LIST, queryParameters).getResponse().getBody();
        List<RestaurantResponse> restaurantResponses = restaurantMapper.toRestaurantListResponse(body.getItems().getItem());
        List<RestaurantResponse> list = restaurantResponses.stream()
                .map((response) -> addInform(response))
                .map((response) -> fetchCategory(response))
                .toList();

        return RestaurantListResponse.builder()
                .list(list)
                .totalCount(body.getTotalCount())
                .build();
    }

    private RestaurantResponse fetchCategory(RestaurantResponse restaurantResponse) {
        String detailQueryParameters = tourApiService.createQueryParameters(Integer.parseInt(restaurantResponse.getContentId()));
        TourApiListResponse.Body detailBody = tourApiService.fetchList(TourApiUrl.DETAIL, detailQueryParameters).getResponse().getBody();
        detailBody.getItems().getItem().stream().findFirst()
                .ifPresent(detail -> {
                    restaurantResponse.setCategory(extractCategory(detail.getOverview()));
                });
        return restaurantResponse;
    }

    private String extractCategory(String overview ) {
        Map<String, FoodCategory> categoryMap = new HashMap<>();
        categoryMap.put("양식", FoodCategory.WESTERN);
        categoryMap.put("일식", FoodCategory.JAPANESE);
        categoryMap.put("한식", FoodCategory.KOREAN);
        categoryMap.put("한정식", FoodCategory.KOREAN);
        categoryMap.put("중식", FoodCategory.CHINESE);
        categoryMap.put("카페", FoodCategory.CAFE);

        try {
            for (String category : categoryMap.keySet()) {
                if (overview.contains(category)) {
                    return categoryMap.get(category).getCategory();
                }
            }
        } catch (Exception e) {}

        return "기타";
    }

    private <T extends RestaurantCommonResponse> T addInform(T restaurantResponse) {
        TourApiDetailIntroResponse.Item item;
        try {
            item = tourApiService.fetchDetailIntro(
                    TourApiUrl.DETAIL_INTRO,
                    tourApiService.createQueryParameters(restaurantResponse.getContentId(), ContentTypeId.RESTAURANT.getId())
            ).getResponse().getBody().getItems().getItem().get(0);

            restaurantResponse.setPlaytime(item.getOpentimefood());

            List<String> list = Arrays.asList(item.getTreatmenu().split("\\s*/\\s*"))
                    .stream().map((food) -> food
                            .replaceAll("\\s*등$", "")
                            .replaceAll("\\s*외$", ""))
                    .toList();
            restaurantResponse.setHashtags(list);
        } catch (Exception e) {
            log.error("Failed to fetch restaurant detail from API", e);
        }

        // 평점
        double rating = reviewService.getRatingById(restaurantResponse.getContentId());
        restaurantResponse.setRating(rating);

        // 리뷰수
        int review = reviewService.getReviewCountById(restaurantResponse.getContentId());
        restaurantResponse.setReview(review);

        // 위시리스트 여부
        boolean wished = wishService.isWished("", restaurantResponse.getContentId());
        restaurantResponse.setWish(wished);

        return restaurantResponse;
    }

    @Override
    public RestaurantListResponse getRestaurantListBySearch(String search) {
        String queryParameters = tourApiService.createQueryParameters(URLEncoder.encode(search), ContentTypeId.RESTAURANT);

        TourApiListResponse.Body body = tourApiService.fetchList(TourApiUrl.SEARCH_LIST, queryParameters).getResponse().getBody();
        List<RestaurantResponse> restaurantListResponse = restaurantMapper.toRestaurantListResponse(body.getItems().getItem());
        List<RestaurantResponse> list = restaurantListResponse.stream()
                .map((response) -> addInform(response))
                .map((response) -> fetchCategory(response))
                .toList();

        return RestaurantListResponse.builder()
                .list(list)
                .totalCount(body.getTotalCount())
                .build();
    }

    @Override
    public RestaurantDetailResponse getRestaurant(int contentId) {
        String queryParameters = tourApiService.createQueryParameters(contentId);

        TourApiListResponse.Body body = tourApiService.fetchList(TourApiUrl.DETAIL, queryParameters)
                .getResponse().getBody();
        List<RestaurantDetailResponse> restaurantResponses = restaurantMapper.toRestaurantDetailListResponse(body.getItems().getItem());
        RestaurantDetailResponse restaurantDetailResponse = restaurantResponses.stream().findFirst()
                .orElseThrow(() -> new NotFound());
        restaurantDetailResponse.setCategory(extractCategory(restaurantDetailResponse.getCategory()));

        return addInform(restaurantDetailResponse);
    }
}
