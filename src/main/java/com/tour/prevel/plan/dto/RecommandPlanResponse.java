package com.tour.prevel.plan.dto;

import lombok.Builder;

@Builder
public record RecommandPlanResponse(
        String id,
        String url
) { }
