package com.tour.prevel.tour.mapper;

import com.tour.prevel.tour.dto.KeywordResponse;
import com.tour.prevel.tour.dto.TourDetailResponse;
import com.tour.prevel.tour.dto.TourResponse;
import com.tour.prevel.tourapi.domain.ContentTypeId;
import com.tour.prevel.tourapi.dto.TourApiListResponse;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TourMapper {

    @IterableMapping(qualifiedByName = "toTourResponse")
    List<TourResponse> toTourListResponse(List<TourApiListResponse.Item> apiResponse);

    @Named("toTourResponse")
    @Mapping(target = "thumbnail", source = "firstimage")
    @Mapping(target = "contentId", source = "contentid")
    @Mapping(target = "lat", source = "mapx")
    @Mapping(target = "lon", source = "mapy")
    @Mapping(target = "contentTypeId", source = "contenttypeid")
    @Mapping(target = "address", expression = "java((apiResponse.getAddr1() + \" \" + apiResponse.getAddr2()).trim())")
    TourResponse toTourResponse(TourApiListResponse.Item apiResponse);

    @IterableMapping(qualifiedByName = "toTourDetailResponse")
    List<TourDetailResponse> toTourDetailListResponse(List<TourApiListResponse.Item> apiResponse);

    @Named("toTourDetailResponse")
    @Mapping(target = "thumbnail", source = "firstimage")
    @Mapping(target = "contentId", source = "contentid")
    @Mapping(target = "contentTypeId", source = "contenttypeid")
    TourDetailResponse toTourDetailResponse(TourApiListResponse.Item apiResponse);

    @IterableMapping(qualifiedByName = "toKeywordResponse")
    List<KeywordResponse> toKeywordListResponse(List<TourApiListResponse.Item> item);

    @Named("toKeywordResponse")
    @Mapping(target = "keyword", source = "title")
    @Mapping(target = "address", source = "addr1")
    @Mapping(target = "category", source = "contenttypeid", qualifiedByName = "getCategory")
    KeywordResponse toKeywordResponse(TourApiListResponse.Item apiResponse);

    @Named("getCategory")
    public static String getCategory(String contentTypeId) {
        return contentTypeId.equals(ContentTypeId.TOUR.getId())
                ? ContentTypeId.TOUR.getId() : ContentTypeId.RESTAURANT.getId();
    }
}
