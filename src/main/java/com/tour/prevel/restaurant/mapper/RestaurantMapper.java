package com.tour.prevel.restaurant.mapper;

import com.tour.prevel.restaurant.dto.RestaurantDetailResponse;
import com.tour.prevel.restaurant.dto.RestaurantResponse;
import com.tour.prevel.tour.domain.Tour;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantMapper {

    @IterableMapping(qualifiedByName = "toRestaurantResponse")
    List<RestaurantResponse> toRestaurantListResponses(List<Tour> tours);

    RestaurantDetailResponse toRestaurantDetailResponse(Tour tour);

    @Named("toRestaurantResponse")
    @Mapping(target = "lat", source = "mapX")
    @Mapping(target = "lon", source = "mapY")
    RestaurantResponse toRestaurantResponse(Tour tour);
}
