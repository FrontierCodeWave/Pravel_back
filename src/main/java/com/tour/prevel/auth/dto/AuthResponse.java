package com.tour.prevel.auth.dto;

import com.tour.prevel.auth.domain.User;
import lombok.Builder;

@Builder
public record AuthResponse(User user, String token) { }