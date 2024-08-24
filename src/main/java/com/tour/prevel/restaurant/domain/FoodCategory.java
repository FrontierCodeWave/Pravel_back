package com.tour.prevel.restaurant.domain;

public enum FoodCategory {
    WESTERN("양식"),
    JAPANESE("일식"),
    KOREAN("한식"),
    CHINESE("중식"),
    CAFE("카페");

    private final String category;

    FoodCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
