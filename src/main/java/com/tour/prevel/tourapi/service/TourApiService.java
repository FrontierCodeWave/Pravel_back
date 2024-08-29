package com.tour.prevel.tourapi.service;

import com.tour.prevel.tourapi.domain.ContentTypeId;
import com.tour.prevel.tourapi.domain.TourApiUrl;
import com.tour.prevel.tourapi.dto.TourApiDetailIntroResponse;
import com.tour.prevel.tourapi.dto.TourApiImageListResponse;
import com.tour.prevel.tourapi.dto.TourApiListResponse;

public interface TourApiService {

    String createQueryParameters(double mapX, double mapY, Integer pageNo, ContentTypeId contentTypeId);
    String createQueryParameters(int contentId);
    String createQueryParameters(String contentId, String contentTypeId);
    String createQueryParameters(String search, ContentTypeId contentTypeId);
    String createQueryParameters(String contentId, int pageNo);

    TourApiListResponse fetchList(TourApiUrl url, String queryParameters);
    TourApiDetailIntroResponse fetchDetailIntro(TourApiUrl url, String queryParameters);
    TourApiImageListResponse fetchImageList(TourApiUrl url, String queryParameters);
}
