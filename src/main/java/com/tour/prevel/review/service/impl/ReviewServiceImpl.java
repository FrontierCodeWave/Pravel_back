package com.tour.prevel.review.service.impl;

import com.tour.prevel.review.repository.ReviewQueryRepository;
import com.tour.prevel.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewQueryRepository reviewQueryRepository;

    @Override
    public int getReviewCountById(String contentId) {
        return reviewQueryRepository.getReviewCountById(contentId);
    }
}
