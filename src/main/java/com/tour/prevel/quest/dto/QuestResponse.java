package com.tour.prevel.quest.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class QuestResponse {

    private Long id;

    private int energy;
    private String title;
}
