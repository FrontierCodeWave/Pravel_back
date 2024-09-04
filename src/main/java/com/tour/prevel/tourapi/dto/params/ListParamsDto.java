package com.tour.prevel.tourapi.dto.params;

import com.tour.prevel.tourapi.domain.ContentTypeId;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ListParamsDto {
    private double mapX;
    private double mapY;
    private Integer pageNo;
    private ContentTypeId contentTypeId;
    private int makers = 10;
    private int radius = 1000;
}
