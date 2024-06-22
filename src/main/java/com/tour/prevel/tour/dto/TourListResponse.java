package com.tour.prevel.tour.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record TourListResponse(List<TourResponse> list, int totalCount) {}