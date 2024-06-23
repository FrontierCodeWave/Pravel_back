package com.tour.prevel.auth.dto;

import lombok.Builder;

@Builder
public record UserResponse(String email) { }
