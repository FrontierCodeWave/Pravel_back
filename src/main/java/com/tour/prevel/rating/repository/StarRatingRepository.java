package com.tour.prevel.rating.repository;

import com.tour.prevel.rating.domain.StarRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StarRatingRepository extends JpaRepository<StarRating, String> {
}
