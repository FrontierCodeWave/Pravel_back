package com.tour.prevel.energy.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record EnergyResponse(
    Long id,
    String location,
    String title,
    boolean used,
    int energy,
    LocalDate expirationDate,
    LocalDate usedDate
) {
}
