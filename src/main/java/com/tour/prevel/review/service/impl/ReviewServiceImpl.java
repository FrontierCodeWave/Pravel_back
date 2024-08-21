package com.tour.prevel.review.service.impl;

import com.tour.prevel.auth.domain.User;
import com.tour.prevel.review.domain.Review;
import com.tour.prevel.review.dto.ReviewListResponse;
import com.tour.prevel.review.repository.ReviewQueryRepository;
import com.tour.prevel.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewQueryRepository reviewQueryRepository;

    @Override
    public int getReviewCountById(String contentId) {
        return reviewQueryRepository.getReviewCountById(contentId);
    }

    @Override
    public double getRatingById(String contentId) {
        List<Review> reviewList = reviewQueryRepository.findByContentId(contentId);

        if (reviewList.size() == 0) {
            return 0;
        }

        double sum = reviewList.stream().mapToDouble(Review::getRating).sum();
        return Math.floor(sum / reviewList.size());
    }

    @Override
    public ReviewListResponse getReviewList(String contentId, Pageable pageable) {
        return reviewQueryRepository.findAllByContentId(contentId, pageable);
    }
}
