package com.tour.prevel.energy.controller;

import com.tour.prevel.auth.utils.JwtUtil;
import com.tour.prevel.energy.dto.EnergyResponse;
import com.tour.prevel.energy.servce.EnergyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/energy")
public class EnergyController {

    private final EnergyService energyService;
    private final JwtUtil jwtUtil;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<EnergyResponse> getEnergyList(@RequestHeader(name = "Authorization") String token) {
        String userId = jwtUtil.getUserId(token);
        return energyService.getEnergyList(userId);
    }
}
