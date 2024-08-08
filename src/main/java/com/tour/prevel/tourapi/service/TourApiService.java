package com.tour.prevel.tourapi.service;

import com.tour.prevel.tourapi.domain.ContentTypeId;
import com.tour.prevel.tourapi.domain.TourApiUrl;
import com.tour.prevel.tourapi.dto.TourApiListResponse;

public interface TourApiService {

    TourApiListResponse fetchListFromTourAPI(TourApiUrl url, String queryParameters);
    String createQueryParameters(double mapX, double mapY, Integer pageNo, ContentTypeId contentTypeId);
    String createQueryParameters(int contentId);
}
