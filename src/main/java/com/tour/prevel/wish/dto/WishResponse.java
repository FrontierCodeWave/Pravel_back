package com.tour.prevel.wish.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class WishResponse {

    private String contentId;
    private String title;
    private String thumbnail;
    private String category;
}
