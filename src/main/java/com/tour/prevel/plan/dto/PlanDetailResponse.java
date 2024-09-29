package com.tour.prevel.plan.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class PlanDetailResponse {

    private long planId;
    private List<ScheduleResponse> schedules;
}
