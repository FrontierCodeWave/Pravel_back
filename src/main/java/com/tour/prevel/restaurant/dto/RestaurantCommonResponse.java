package com.tour.prevel.restaurant.dto;

import java.util.List;

public interface RestaurantCommonResponse {
    String getContentId();

    void setPlaytime(String playtime);
    void setWish(boolean wish);
    void setReview(int review);
    void setRating(double rating);
    void setTreatmenu(List<String> treatmenu);
}
