package com.tour.prevel.restaurant.dto;

public record RestaurantListRequest(
        double x,
        double y,
        Integer pageNo,
        Integer makers,
        Integer radius
) {
}
