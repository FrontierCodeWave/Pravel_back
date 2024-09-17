package com.tour.prevel.tour.controller;

import com.tour.prevel.tour.dto.*;
import com.tour.prevel.tour.service.TourService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PageableDefault;
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

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{contentId}/image")
    @ApiOperation("관광지 이미지 조회")
    public TourImageListResponse getTourImage(
            @PathVariable String contentId,
            @PageableDefault(size = 9, page = 1) @RequestParam(required = false, defaultValue = "1") int page) {
        return tourService.getTourImage(contentId, page);
    }
}
