package com.tour.prevel.tour.service;

import com.tour.prevel.tour.dto.TourDetailResponse;
import com.tour.prevel.tour.dto.TourListRequest;
import com.tour.prevel.tour.dto.TourListResponse;
import com.tour.prevel.tour.dto.TourResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TourService {
    TourListResponse getTourList(TourListRequest request);
    TourListResponse getTourListBySearch(String search);
    TourDetailResponse getTour(int contentId);
    List<String> getTourImage(String contentId, int page);
}
