package com.tour.prevel.plan.dto;

import lombok.Builder;

@Builder
public record PlanHistoryResponse(
        String id,
        String title,
        String url,
        String startDate,
        String endDate
) { }
