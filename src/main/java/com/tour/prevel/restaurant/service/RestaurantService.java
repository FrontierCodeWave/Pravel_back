package com.tour.prevel.restaurant.service;

import com.tour.prevel.restaurant.dto.RestaurantDetailResponse;
import com.tour.prevel.restaurant.dto.RestaurantListRequest;
import com.tour.prevel.restaurant.dto.RestaurantListResponse;

import java.util.List;

public interface RestaurantService {

    RestaurantListResponse getRestaurantList(RestaurantListRequest request, String userId);
    RestaurantListResponse getRestaurantListBySearch(String search, String userId);
    RestaurantDetailResponse getRestaurant(int contentId, String userId);
}
