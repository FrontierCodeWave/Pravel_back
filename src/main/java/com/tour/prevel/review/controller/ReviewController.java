package com.tour.prevel.review.controller;

import com.tour.prevel.auth.domain.User;
import com.tour.prevel.review.dto.ReviewListResponse;
import com.tour.prevel.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("tour/{contentId}/review")
    public ReviewListResponse getReviewList(
            @PathVariable String contentId,
            @PageableDefault(size = 4, direction = Sort.Direction.DESC, sort = "createdTime") Pageable page
            ) {
        return reviewService.getReviewList(contentId, page);
    }
}
