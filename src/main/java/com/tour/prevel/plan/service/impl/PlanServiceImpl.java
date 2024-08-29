package com.tour.prevel.plan.service.impl;

import com.tour.prevel.plan.domain.Plan;
import com.tour.prevel.plan.domain.PlanStatus;
import com.tour.prevel.plan.dto.CreatePlanRequest;
import com.tour.prevel.plan.dto.ScheduleResponse;
import com.tour.prevel.plan.mapper.PlanMapper;
import com.tour.prevel.plan.repository.PlanQueryRepository;
import com.tour.prevel.plan.repository.PlanRepository;
import com.tour.prevel.plan.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;
    private final PlanQueryRepository planQueryRepository;
    private final PlanMapper planMapper;

    @Override
    public void createPlan(CreatePlanRequest request) {
        planRepository.save(Plan.builder()
                .title(request.title())
                .adult(request.adult())
                .child(request.child())
                .location(request.location())
                .startDate(LocalDate.parse(request.startDate()))
                .endDate(LocalDate.parse(request.endDate()))
                .status(PlanStatus.REVISION)
                .build());
    }

    @Override
    public List<ScheduleResponse> getScheduleList(String id, LocalDate date) {
        return planMapper.toScheduleResponseList(planQueryRepository.getScheduleList(id, date));
    }
}