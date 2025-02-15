package com.tour.prevel.plan.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class PlanDetailResponse {

    private long planId;
    private String startDate;
    private String endDate;
    private String startPoint;
    private String endPoint;
    private String location;
    private int adult;
    private int child;
    private String url;
    private List<ScheduleResponse> schedules;
}
