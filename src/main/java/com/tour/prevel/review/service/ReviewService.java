package com.tour.prevel.review.service;

public interface ReviewService {

    int getReviewCountById(String contentId);
    double getRatingById(String contentId);
}
