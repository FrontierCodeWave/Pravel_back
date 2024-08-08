package com.tour.prevel.restaurant.controller;

import com.tour.prevel.restaurant.dto.RestaurantDetailResponse;
import com.tour.prevel.restaurant.dto.RestaurantListRequest;
import com.tour.prevel.restaurant.dto.RestaurantListResponse;
import com.tour.prevel.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public RestaurantListResponse getTourList(@RequestBody RestaurantListRequest request) {
        return restaurantService.getRestaurantList(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{contentId}")
    public RestaurantDetailResponse getTour(@PathVariable String contentId) {
        return restaurantService.getRestaurant(Integer.parseInt(contentId));
    }
}
