package com.tour.prevel.restaurant.controller;

import com.tour.prevel.restaurant.dto.RestaurantDetailResponse;
import com.tour.prevel.restaurant.dto.RestaurantListRequest;
import com.tour.prevel.restaurant.dto.RestaurantListResponse;
import com.tour.prevel.restaurant.service.RestaurantService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/food")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    @ApiOperation("맛집 리스트 조회(버튼)")
    public RestaurantListResponse getTourList(
            @RequestBody RestaurantListRequest request,
            Authentication auth) {
        return restaurantService.getRestaurantList(request, auth.getName());
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/search")
    @ApiOperation("맛집 리스트 조회(검색)")
    public RestaurantListResponse getTourListBySearch(
            @RequestBody String search,
            Authentication auth) {
        return restaurantService.getRestaurantListBySearch(search, auth.getName());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{contentId}")
    @ApiOperation("맛집 상세 정보 조회")
    public RestaurantDetailResponse getTour(
            @PathVariable String contentId,
            Authentication auth) {
        return restaurantService.getRestaurant(Integer.parseInt(contentId), auth.getName());
    }
}
