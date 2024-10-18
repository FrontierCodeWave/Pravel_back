package com.tour.prevel.tour.mapper;

import com.tour.prevel.tour.domain.Tour;
import com.tour.prevel.tour.dto.KeywordResponse;
import com.tour.prevel.tour.dto.TourDetailResponse;
import com.tour.prevel.tour.dto.TourResponse;
import com.tour.prevel.tourapi.domain.ContentTypeId;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TourMapper {

    @IterableMapping(qualifiedByName = "toTourResponse")
    List<TourResponse> toTourListResponses(List<Tour> tours);

    @IterableMapping(qualifiedByName = "toKeywordResponse")
    List<KeywordResponse> toKeywordListResponses(List<Tour> tours);

    TourDetailResponse toTourDetailResponse(Tour tour);

    @Named("toKeywordResponse")
    @Mapping(target = "keyword", source = "title")
    @Mapping(target = "address", source = "addr1")
    @Mapping(target = "category", source = "contentTypeId", qualifiedByName = "getCategory")
    KeywordResponse toKeywordResponse(Tour tour);

    @Named("toTourResponse")
    @Mapping(target = "lat", source = "mapX")
    @Mapping(target = "lon", source = "mapY")
    TourResponse toTourResponse(Tour tour);

    @Named("getCategory")
    public static String getCategory(String contentTypeId) {
        return contentTypeId.equals(ContentTypeId.TOUR.getId())
                ? ContentTypeId.TOUR.getId() : ContentTypeId.RESTAURANT.getId();
    }
}
