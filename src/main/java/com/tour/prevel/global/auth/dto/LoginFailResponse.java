package com.tour.prevel.global.auth.dto;

import lombok.Builder;

@Builder
public record LoginFailResponse(String message) {
}
