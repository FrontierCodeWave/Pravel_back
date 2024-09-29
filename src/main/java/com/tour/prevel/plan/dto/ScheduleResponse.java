package com.tour.prevel.plan.dto;

import com.tour.prevel.plan.domain.ScheduleCategory;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
public record ScheduleResponse(
        String id,
        String category,
        String title,
        String description,
        int order,
        String date,
        String thumbnail
) {
}
