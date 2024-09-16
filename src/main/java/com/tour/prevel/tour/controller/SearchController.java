package com.tour.prevel.tour.controller;

import com.tour.prevel.tour.dto.KeywordResponse;
import com.tour.prevel.tour.service.TourService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/search")
public class SearchController {

    private final TourService tourService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @ApiOperation("키워드 조회")
    public List<KeywordResponse> getKeywordList(
            String keyword
    ) {
        return tourService.getKeywordList(keyword);
    }
}
