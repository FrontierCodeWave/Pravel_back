package com.tour.prevel.plan.dto;

public record CreatePlanRequest(
    String location,
    String startPoint,
    String endPoint,
    String title,
    int adult,
    int child,
    String startDate,
    String endDate
) {
}
