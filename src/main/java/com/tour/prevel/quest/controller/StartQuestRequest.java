package com.tour.prevel.quest.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StartQuestRequest {

    @NotNull
    private Integer planId;
    @NotBlank
    private String date;
}
