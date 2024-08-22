package com.tour.prevel.energy.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record EnergyResponse(
    String location,
    String title,
    boolean used,
    LocalDate expirationDate,
    LocalDate usedDate
) {
}
