package com.tour.prevel.plan.dto;

import jakarta.validation.constraints.NotBlank;

public record CreatePlanRequest(
    @NotBlank String location,
    @NotBlank String startPoint,
    @NotBlank String endPoint,
    String title,
    int adult,
    int child,
    @NotBlank String startDate,
    @NotBlank String endDate
) {
}
