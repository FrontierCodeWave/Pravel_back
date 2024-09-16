package com.tour.prevel.plan.service;

import com.tour.prevel.plan.dto.PlanHistoryResponse;
import com.tour.prevel.plan.dto.RecommandPlanResponse;
import com.tour.prevel.plan.dto.CreatePlanRequest;
import com.tour.prevel.plan.dto.ScheduleResponse;

import java.time.LocalDate;
import java.util.List;

public interface PlanService {
    void createPlan(CreatePlanRequest request, String userId);
    List<ScheduleResponse> getScheduleList(String id, LocalDate date);
    List<RecommandPlanResponse> getRecommandPlanList(String userId);
    List<PlanHistoryResponse> getPlanHistoryList(String userId);
    PlanHistoryResponse getPlanRecord(Long id, String name);
    int getPlanCount(String userId);
}
