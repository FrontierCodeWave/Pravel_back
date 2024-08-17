package com.tour.prevel.tour.controller;

import com.tour.prevel.tour.dto.TourDetailResponse;
import com.tour.prevel.tour.dto.TourListRequest;
import com.tour.prevel.tour.dto.TourListResponse;
import com.tour.prevel.tour.service.TourService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tour")
public class TourController {

    private final TourService tourService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    @ApiOperation("관광지 리스트 조회(버튼)")
    public TourListResponse getTourList(@RequestBody TourListRequest request) {
        return tourService.getTourList(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/search")
    @ApiOperation("관광지 리스트 조회(검색)")
    public TourListResponse getTourListBySearch(@RequestBody String search) {
        return tourService.getTourListBySearch(search);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{contentId}")
    @ApiOperation("관광지 상세 정보 조회")
    public TourDetailResponse getTour(@PathVariable String contentId) {
        return tourService.getTour(Integer.parseInt(contentId));
    }
}
