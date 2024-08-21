package com.tour.prevel.review.service;

import com.tour.prevel.auth.domain.User;
import com.tour.prevel.review.dto.ReviewListResponse;
import org.springframework.data.domain.Pageable;

public interface ReviewService {

    int getReviewCountById(String contentId);
    double getRatingById(String contentId);
    ReviewListResponse getReviewList(String contentId, Pageable pageable);
}
