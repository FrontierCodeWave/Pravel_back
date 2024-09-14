package com.tour.prevel.tour.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record TourImageListResponse(List<String> list, int totalCount) {}