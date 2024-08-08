package com.tour.prevel.restaurant.service;

import com.tour.prevel.restaurant.dto.RestaurantDetailResponse;
import com.tour.prevel.restaurant.dto.RestaurantListRequest;
import com.tour.prevel.restaurant.dto.RestaurantListResponse;

public interface RestaurantService {

    RestaurantListResponse getRestaurantList(RestaurantListRequest request);
    RestaurantDetailResponse getRestaurant(int contentId);
}
