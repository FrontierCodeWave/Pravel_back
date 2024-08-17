package com.tour.prevel.rating.service.impl;

import com.tour.prevel.rating.domain.StarRating;
import com.tour.prevel.rating.repository.StarRatingQueryRepository;
import com.tour.prevel.rating.service.StarRatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StarRatingServiceImpl implements StarRatingService {

    private final StarRatingQueryRepository ratingQueryRepository;

    @Override
    public double getRatingById(String contentId) {
        List<StarRating> starRatingList = ratingQueryRepository.findByContentId(contentId);

        if (starRatingList.size() == 0) {
            return 0;
        }

        double sum = starRatingList.stream().mapToDouble(StarRating::getRating).sum();
        return Math.floor(sum / starRatingList.size());
    }
}
