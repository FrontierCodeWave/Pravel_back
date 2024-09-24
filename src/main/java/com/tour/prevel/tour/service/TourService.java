package com.tour.prevel.tour.service;

import com.tour.prevel.tour.dto.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TourService {
    TourListResponse getTourList(TourListRequest request, String userId);
    TourListResponse getTourListBySearch(String search, String userId);
    TourDetailResponse getTour(int contentId, String userId);
    TourImageListResponse getTourImage(String contentId, int page);
    List<KeywordResponse> getKeywordList(String keyword);
}
