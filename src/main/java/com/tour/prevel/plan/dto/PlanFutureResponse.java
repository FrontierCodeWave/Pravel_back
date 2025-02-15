package com.tour.prevel.plan.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PlanFutureResponse {

    private String id;
    private String title;
    private String url;
    private String startDate;
    private String endDate;
    private String location;
    private String startPoint;
    private String endPoint;
}
