package com.tour.prevel.review.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record ReviewListResponse(List<ReviewResponse> list, int total) {}
