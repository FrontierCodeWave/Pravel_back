package com.tour.prevel.tour.dto;

public interface TourCommonResponse {
    String getContentId();
    String getContentTypeId();

    void setPlaytime(String playtime);
    void setWish(boolean wish);
    void setReview(int review);
    void setRating(double rating);
}
