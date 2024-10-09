package com.tour.prevel.plan.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateScheduleRequest {

    @NotNull
    private Integer planId;
    @NotBlank
    private String date;
    @NotBlank
    private String category;
    @NotBlank
    private String title;
    private String thumbnail;
    private String description;
}
