package com.tour.prevel.global.auth.dto;

import com.tour.prevel.auth.domain.User;
import lombok.Builder;

@Builder
public record LoginSuccessResponse(User user, String token) { }