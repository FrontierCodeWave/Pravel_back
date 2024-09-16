package com.tour.prevel.tour.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class KeywordResponse {
    private String keyword;
    private String address;
    private String category;
}
