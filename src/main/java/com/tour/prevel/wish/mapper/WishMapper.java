package com.tour.prevel.wish.mapper;

import com.tour.prevel.restaurant.dto.RestaurantResponse;
import com.tour.prevel.tour.domain.Tour;
import com.tour.prevel.tourapi.domain.ContentTypeId;
import com.tour.prevel.wish.dto.WishResponse;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WishMapper {

    @IterableMapping(qualifiedByName = "toWishResponse")
    List<WishResponse> toWishListResponses(List<Tour> tours);

    @Named("toWishResponse")
    @Mapping(target = "contentType", source = "contentTypeId", qualifiedByName = "getCategory")
    WishResponse toWishResponse(Tour tour);

    @Named("getCategory")
    public static String getCategory(String contentTypeId) {
        return contentTypeId.equals(ContentTypeId.TOUR.getId())
                ? "TOUR" : "FOOD";
    }
}
