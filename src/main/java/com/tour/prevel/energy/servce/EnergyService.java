package com.tour.prevel.energy.servce;

import com.tour.prevel.energy.dto.EnergyListResponse;

public interface EnergyService {
    int getCurrentEnergyCount(String userId);
    EnergyListResponse getEnergyList(String userId);
}
