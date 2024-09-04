package com.tour.prevel.tour.dto;

import lombok.Builder;

@Builder
public record TourListRequest(
        double x,
        double y,
        Integer pageNo,
        int makers,
        int radius
) { }
