package com.tour.prevel.tour.service;

import com.tour.prevel.tour.dto.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TourService {
    TourListResponse getTourList(TourListRequest request);
    TourListResponse getTourListBySearch(String search);
    TourDetailResponse getTour(int contentId);
    TourImageListResponse getTourImage(String contentId, int page);
}
