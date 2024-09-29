package com.tour.prevel.plan.repository;

import com.tour.prevel.plan.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
