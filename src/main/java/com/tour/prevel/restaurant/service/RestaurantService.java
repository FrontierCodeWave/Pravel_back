package com.tour.prevel.restaurant.service;

import com.tour.prevel.restaurant.dto.RestaurantDetailResponse;
import com.tour.prevel.restaurant.dto.RestaurantListRequest;
import com.tour.prevel.restaurant.dto.RestaurantListResponse;
import com.tour.prevel.tour.dto.TourListResponse;

import java.util.List;

public interface RestaurantService {

    RestaurantListResponse getRestaurantList(RestaurantListRequest request);
    RestaurantListResponse getRestaurantListBySearch(String search);
    RestaurantDetailResponse getRestaurant(int contentId);
    List<String> getRestaurantImage(String contentId, int page);
}
