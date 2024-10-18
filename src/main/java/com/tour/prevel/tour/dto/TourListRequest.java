package com.tour.prevel.tour.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TourListRequest {

    private double x;
    private double y;
    private int markers = 10;
    private int radius = 1000;
}
