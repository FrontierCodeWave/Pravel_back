package com.tour.prevel.tour.repository;

import com.tour.prevel.tour.domain.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TourRepository extends JpaRepository<Tour, Long> {
    Optional<Tour> findByContentId(String contentId);
}
