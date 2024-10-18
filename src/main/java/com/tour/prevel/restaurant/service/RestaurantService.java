package com.tour.prevel.restaurant.service;

import com.tour.prevel.restaurant.dto.RestaurantDetailResponse;
import com.tour.prevel.restaurant.dto.RestaurantListRequest;
import com.tour.prevel.restaurant.dto.RestaurantListResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RestaurantService {

    RestaurantListResponse getRestaurantList(RestaurantListRequest request, Pageable pageNo, String userId);
    RestaurantDetailResponse getRestaurant(int contentId, String userId);

}
