package com.tour.prevel.energy.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record EnergyListResponse(List<EnergyResponse> list) {
}
