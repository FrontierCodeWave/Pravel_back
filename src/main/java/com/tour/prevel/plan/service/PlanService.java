package com.tour.prevel.plan.service;

import com.tour.prevel.plan.dto.CreatePlanRequest;
import com.tour.prevel.plan.dto.ScheduleResponse;

import java.time.LocalDate;
import java.util.List;

public interface PlanService {
    void createPlan(CreatePlanRequest request);
    List<ScheduleResponse> getScheduleList(String id, LocalDate date);
}
