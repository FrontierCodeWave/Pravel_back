package com.tour.prevel.plan.service;

import com.tour.prevel.plan.dto.*;
import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;

public interface PlanService {
    void createPlan(CreatePlanRequest request, String userId);
    List<ScheduleResponse> getScheduleListByDate(String id, LocalDate date);
    List<RecommandPlanResponse> getRecommandPlanList(String userId);
    List<PlanHistoryResponse> getPlanHistoryList(String userId);
    PlanHistoryResponse getPlanRecord(Long id, String name);
    int getPlanCount(String userId);
    PlanDetailResponse getPlanDetail(String userId);
    void createScheudle(@Valid CreateScheduleRequest request);
    List<PlanFutureResponse> getFuturePlan(String userId);
    void deletePlan(String userId, Long id);
    void activePlan(String userId, Long id);
    int getPlanTotal(String name);
}
