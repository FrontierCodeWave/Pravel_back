package com.tour.prevel.restaurant.mapper;

import com.tour.prevel.restaurant.dto.RestaurantDetailResponse;
import com.tour.prevel.restaurant.dto.RestaurantResponse;
import com.tour.prevel.tourapi.dto.TourApiListResponse;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantMapper {

    @IterableMapping(qualifiedByName = "toRestaurantResponse")
    List<RestaurantResponse> toRestaurantListResponse(List<TourApiListResponse.Item> apiResponse);

    @Named("toRestaurantResponse")
    @Mapping(target = "thumbnail", source = "firstimage")
    @Mapping(target = "contentId", source = "contentid")
    @Mapping(target = "lat", source = "mapx")
    @Mapping(target = "lon", source = "mapy")
    @Mapping(target = "address", expression = "java((apiResponse.getAddr1() + \" \" + apiResponse.getAddr2()).trim())")
    RestaurantResponse toRestaurantResponse(TourApiListResponse.Item apiResponse);

    @IterableMapping(qualifiedByName = "toRestaurantDetailResponse")
    List<RestaurantDetailResponse> toRestaurantDetailListResponse(List<TourApiListResponse.Item> apiResponse);

    @Named("toRestaurantDetailResponse")
    @Mapping(target = "thumbnail", source = "firstimage")
    @Mapping(target = "contentId", source = "contentid")
    @Mapping(target = "category", source = "overview")
    RestaurantDetailResponse toRestaurantDetailResponse(TourApiListResponse.Item apiResponse);
}
