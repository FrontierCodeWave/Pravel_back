package com.tour.prevel.plan.domain;

public enum ScheduleCategory {
    TOUR("tour"),
    FOOD("food")
    ;

    private String category;

    ScheduleCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
