package com.tour.prevel.tour.mapper;

import com.tour.prevel.tour.dto.TourDetailResponse;
import com.tour.prevel.tour.dto.TourResponse;
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
}
