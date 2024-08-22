package com.tour.prevel.energy.controller;

import com.tour.prevel.energy.dto.EnergyListResponse;
import com.tour.prevel.energy.servce.EnergyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/energy")
public class EnergyController {

    private final EnergyService energyService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public EnergyListResponse getEnergyList() {
        return energyService.getEnergyList("test@test.com");
    }
}
