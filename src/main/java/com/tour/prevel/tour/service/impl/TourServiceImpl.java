package com.tour.prevel.tour.service.impl;

import com.tour.prevel.global.exception.NotFound;
import com.tour.prevel.rating.service.StarRatingService;
import com.tour.prevel.review.service.ReviewService;
import com.tour.prevel.tour.dto.TourDetailResponse;
import com.tour.prevel.tour.dto.TourListRequest;
import com.tour.prevel.tour.dto.TourListResponse;
import com.tour.prevel.tour.dto.TourResponse;
import com.tour.prevel.tourapi.domain.ContentTypeId;
import com.tour.prevel.tourapi.domain.TourApiUrl;
import com.tour.prevel.tourapi.dto.TourApiDetailIntroResponse;
import com.tour.prevel.tourapi.service.TourApiService;
import com.tour.prevel.tourapi.dto.TourApiListResponse;
import com.tour.prevel.tour.mapper.TourMapper;
import com.tour.prevel.tour.service.TourService;
import com.tour.prevel.wish.service.WishService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class TourServiceImpl implements TourService {


    private final TourMapper tourMapper;
    private final TourApiService tourApiService;
    private final StarRatingService ratingService;
    private final ReviewService reviewService;
    private final WishService wishService;

    @Override
    public TourListResponse getTourList(TourListRequest request) {
        String queryParameters = tourApiService.createQueryParameters(request.x(), request.y(), request.pageNo(), ContentTypeId.TOUR);

        TourApiListResponse.Body body = tourApiService.fetchList(TourApiUrl.LIST, queryParameters).getResponse().getBody();
        List<TourResponse> tourResponses = tourMapper.toTourListResponse(body.getItems().getItem());
        List<TourResponse> list = tourResponses.stream()
                .map((response) -> addInform(response))
                .toList();

        return TourListResponse.builder()
                .list(list)
                .totalCount(body.getTotalCount())
                .build();
    }

    private TourResponse addInform(TourResponse tourResponse) {
        TourApiDetailIntroResponse.Item item = tourApiService.fetchDetailIntro(
                TourApiUrl.DETAIL_INTRO,
                tourApiService.createQueryParameters(tourResponse.getContentId(), tourResponse.getContentTypeId())
        ).getResponse().getBody().getItems().getItem().get(0);

        // 영업시간
        switch (item.getContenttypeid()) {
            case "12":
                tourResponse.setPlaytime(item.getUsetime());
                break;
            case "14":
                tourResponse.setPlaytime(item.getUsetimeculture());
                break;
            case "15":
                tourResponse.setPlaytime(item.getPlaytime());
                break;
            case "25":
                tourResponse.setPlaytime(item.getTaketime());
                break;
            case "28":
                tourResponse.setPlaytime(item.getOpentime());
                break;
        }

        // 평점
        double rating = ratingService.getRatingById(tourResponse.getContentId());
        tourResponse.setRating(rating);

        // 리뷰수
        int review = reviewService.getReviewCountById(tourResponse.getContentId());
        tourResponse.setReview(review);

        // 위시리스트 여부
        boolean wished = wishService.isWished("", tourResponse.getContentId());
        tourResponse.setWish(wished);

        return tourResponse;
    }

    @Override
    public TourDetailResponse getTour(int contentId) {
        String queryParameters = tourApiService.createQueryParameters(contentId);

        TourApiListResponse.Body body = tourApiService.fetchList(TourApiUrl.DETAIL, queryParameters)
                .getResponse().getBody();
        List<TourDetailResponse> tourResponses = tourMapper.toTourDetailListResponse(body.getItems().getItem());
        return tourResponses.stream().findFirst()
                .orElseThrow(() -> new NotFound());
    }
}
