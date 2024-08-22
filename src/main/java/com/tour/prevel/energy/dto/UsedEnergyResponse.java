package com.tour.prevel.energy.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record UsedEnergyResponse(
        int usedEnergy,
        LocalDate usedDate
) {
}
