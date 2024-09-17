package com.tour.prevel.energy.servce.impl;

import com.tour.prevel.energy.dto.EnergyResponse;
import com.tour.prevel.energy.repository.EnergyQueryRepository;
import com.tour.prevel.energy.servce.EnergyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnergyServiceImpl implements EnergyService {

    private final EnergyQueryRepository energyQueryRepository;
    private final int REWARD_THRESHOLD = 100;

    @Override
    public int getCurrentEnergyCount(String userId) {
        return energyQueryRepository.getCurrentEnergyCount(userId);
    }

    @Override
    public List<EnergyResponse> getEnergyListById(String userId, boolean used) {
        return energyQueryRepository.getEnergyListById(userId, used);
    }

    @Override
    public boolean isRewardAvailable(String userId) {
        return getCurrentEnergyCount(userId) >= REWARD_THRESHOLD;
    }
}
