package com.tour.prevel.plan.controller;

import com.tour.prevel.plan.dto.PlanHistoryResponse;
import com.tour.prevel.plan.dto.RecommandPlanResponse;
import com.tour.prevel.plan.dto.CreatePlanRequest;
import com.tour.prevel.plan.dto.ScheduleResponse;
import com.tour.prevel.plan.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/plan")
public class PlanController {

    private final PlanService planService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public void createPlan(
            @RequestBody CreatePlanRequest request,
            Authentication auth
    ) {
        planService.createPlan(request, auth.getName());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}/schedule")
    public List<ScheduleResponse> getScheduleList(
            @PathVariable String id,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
    ) {
        return planService.getScheduleList(id, date);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/recommand")
    public List<RecommandPlanResponse> getRecommandPlanList(
            Authentication auth
    ) {
        return planService.getRecommandPlanList(auth.getName());
    }

    @ResponseStatus
    @GetMapping("/history")
    public List<PlanHistoryResponse> getPlanHistoryList(
            Authentication auth
    ) {
        return planService.getPlanHistoryList(auth.getName());
    }
}
