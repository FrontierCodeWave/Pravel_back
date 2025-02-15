package com.tour.prevel.plan.controller;

import com.tour.prevel.plan.dto.*;
import com.tour.prevel.plan.service.PlanService;
import jakarta.validation.Valid;
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
    @GetMapping("/total")
    public int getPlanTotal(
            Authentication auth
    ) {
        return planService.getPlanTotal(auth.getName());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public PlanDetailResponse getPlanDetail(
            Authentication auth
    ) {
        return planService.getPlanDetail(auth.getName());
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/onboarding")
    public void createPlan(
            @RequestBody @Valid CreatePlanRequest request,
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
        return planService.getScheduleListByDate(id, date);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/schedule")
    public void createScheudle(
            @RequestBody @Valid CreateScheduleRequest request) {
        planService.createScheudle(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/recommand")
    public List<RecommandPlanResponse> getRecommandPlanList(
            Authentication auth
    ) {
        return planService.getRecommandPlanList(auth.getName());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/history")
    public List<PlanHistoryResponse> getPlanHistoryList(
            Authentication auth
    ) {
        return planService.getPlanHistoryList(auth.getName());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}/record")
    public PlanHistoryResponse getPlanRecord(
            @PathVariable Long id,
            Authentication auth
    ) {
        return planService.getPlanRecord(id, auth.getName());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/count")
    public int getPlanCount(
            Authentication auth
    ) {
        return planService.getPlanCount(auth.getName());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/future")
    public List<PlanFutureResponse> getFuturePlan(
            Authentication auth
    ) {
        return planService.getFuturePlan(auth.getName());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void deletePlan(
            Authentication auth,
            @PathVariable Long id
    ) {
        planService.deletePlan(auth.getName(), id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("{id}")
    public void activePlan(
            Authentication auth,
            @PathVariable Long id
    ) {
        planService.activePlan(auth.getName(), id);
    }
}
