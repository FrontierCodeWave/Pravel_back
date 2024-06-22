package com.tour.prevel.tour.service;

import com.tour.prevel.tour.dto.TourDetailResponse;
import com.tour.prevel.tour.dto.TourListRequest;
import com.tour.prevel.tour.dto.TourListResponse;
import com.tour.prevel.tour.dto.TourResponse;

public interface TourService {
    TourListResponse getTourList(TourListRequest request);
    TourDetailResponse getTour(int contentId);
}
