package com.tour.prevel.restaurant.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record RestaurantListResponse(List<RestaurantResponse> list, int totalCount) {
}
