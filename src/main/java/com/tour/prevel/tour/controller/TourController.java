package com.tour.prevel.tour.controller;

import com.tour.prevel.tour.dto.TourDetailResponse;
import com.tour.prevel.tour.dto.TourListRequest;
import com.tour.prevel.tour.dto.TourListResponse;
import com.tour.prevel.tour.service.TourService;
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
    public TourListResponse getTourList(@RequestBody TourListRequest request) {
        return tourService.getTourList(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{contentId}")
    public TourDetailResponse getTour(@PathVariable String contentId) {
        return tourService.getTour(Integer.parseInt(contentId));
    }
}
