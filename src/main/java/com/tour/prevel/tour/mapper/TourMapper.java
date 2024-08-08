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
    @Mapping(target = "firstImage", source = "firstimage")
    @Mapping(target = "contentId", source = "contentid")
    TourResponse toTourResponse(TourApiListResponse.Item apiResponse);

    @IterableMapping(qualifiedByName = "toTourDetailResponse")
    List<TourDetailResponse> toTourDetailListResponse(List<TourApiListResponse.Item> apiResponse);

    @Named("toTourDetailResponse")
    @Mapping(target = "firstImage", source = "firstimage")
    @Mapping(target = "contentId", source = "contentid")
    TourDetailResponse toTourDetailResponse(TourApiListResponse.Item apiResponse);
}
