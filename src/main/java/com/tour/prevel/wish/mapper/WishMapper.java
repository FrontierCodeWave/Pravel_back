package com.tour.prevel.wish.mapper;

import com.tour.prevel.restaurant.dto.RestaurantResponse;
import com.tour.prevel.tour.domain.Tour;
import com.tour.prevel.wish.dto.WishResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WishMapper {

    List<WishResponse> toWishListResponses(List<Tour> tours);
}
