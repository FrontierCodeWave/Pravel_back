package com.tour.prevel.energy.servce.impl;

import com.tour.prevel.energy.dto.EnergyListResponse;
import com.tour.prevel.energy.repository.EnergyQueryRepository;
import com.tour.prevel.energy.servce.EnergyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnergyServiceImpl implements EnergyService {

    private final EnergyQueryRepository energyQueryRepository;

    @Override
    public int getCurrentEnergyCount(String userId) {
        return energyQueryRepository.getCurrentEnergyCount(userId);
    }

    @Override
    public EnergyListResponse getEnergyList(String userId) {
        return energyQueryRepository.getEnergyList(userId);
    }
}
