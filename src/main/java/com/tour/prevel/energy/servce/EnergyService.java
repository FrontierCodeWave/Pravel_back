package com.tour.prevel.energy.servce;

import com.tour.prevel.energy.dto.EnergyResponse;

import java.util.List;

public interface EnergyService {
    int getCurrentEnergyCount(String userId);
    List<EnergyResponse> getEnergyListById(String userId, boolean used);
    boolean isRewardAvailable(String userId);
}
