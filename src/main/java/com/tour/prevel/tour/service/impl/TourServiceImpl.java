package com.tour.prevel.tour.service.impl;

import com.tour.prevel.global.exception.NotFound;
import com.tour.prevel.review.service.ReviewService;
import com.tour.prevel.tour.domain.Tour;
import com.tour.prevel.tour.dto.*;
import com.tour.prevel.tour.repository.TourImageRepository;
import com.tour.prevel.tour.repository.TourQueryRepository;
import com.tour.prevel.tour.repository.TourRepository;
import com.tour.prevel.tourapi.domain.ContentTypeId;
import com.tour.prevel.tour.mapper.TourMapper;
import com.tour.prevel.tour.service.TourService;
import com.tour.prevel.wish.service.WishService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TourServiceImpl implements TourService {

    private final TourMapper tourMapper;
    private final ReviewService reviewService;
    private final WishService wishService;

    private final TourRepository tourRepository;
    private final TourImageRepository tourImageRepository;

    private final TourQueryRepository tourQueryRepository;

    @Override
    public TourListResponse getTourList(TourListRequest request, Pageable pageNo, String userId) {
        List<Tour> tours = tourQueryRepository.findTourWithinRadius(request.getX(), request.getY(), request.getRadius(), pageNo, ContentTypeId.TOUR);

        List<TourResponse> tourResponses = tourMapper.toTourListResponses(tours);
        List<TourResponse> list = tourResponses.stream()
                .map((response) -> {
                    response.setCategory("관광");
                    return addInform(response, userId);
                })
                .toList();

        return TourListResponse.builder()
                .list(list)
                .totalCount(tourQueryRepository.findTourCountWithinRadius(request.getX(), request.getY(), request.getRadius(), ContentTypeId.TOUR))
                .build();
    }

    private <T extends TourCommonResponse> T addInform(T tourResponse, String userId) {

        // 평점
        double rating = reviewService.getRatingById(tourResponse.getContentId());
        tourResponse.setRating(rating);

        // 리뷰수
        int review = reviewService.getReviewCountById(tourResponse.getContentId());
        tourResponse.setReview(review);

        // 위시리스트 여부
        boolean wished = wishService.isWished(userId, tourResponse.getContentId());
        tourResponse.setWish(wished);

        return tourResponse;
    }

    @Override
    public TourDetailResponse getTour(int contentId, String userId) {
        TourDetailResponse tourDetailResponse = tourMapper.toTourDetailResponse(
                tourRepository.findByContentId(String.valueOf(contentId)).orElseThrow(() -> new NotFound())
        );

        return addInform(tourDetailResponse, userId);
    }

    @Override
    public TourImageListResponse getTourImage(String contentId, int page) {
        String tourImage = tourQueryRepository.findTourImageById(contentId);
        List<String> list = Arrays.stream(tourImage.split(","))
                .filter((image) -> !image.isEmpty())
                .toList();

        int start = (page - 1) * 9;
        int end = Math.min(start + 9, list.size());
        list = list.subList(start, end);

        return TourImageListResponse.builder()
                .list(list)
                .totalCount(list.size())
                .build();
    }

    @Override
    public List<KeywordResponse> getKeywordList(String keyword) {
        ContentTypeId[] params = {ContentTypeId.TOUR, ContentTypeId.RESTAURANT};
        return Arrays.stream(params).toList().stream()
                .map((contentTypeId) ->
                        tourQueryRepository.findTourByKeyword(keyword, contentTypeId))
                .map((tours) ->
                        tourMapper.toKeywordListResponses(tours))
                .flatMap(List::stream)
                .toList();
    }


}
