package com.tour.prevel.plan.service.impl;

import com.tour.prevel.auth.domain.User;
import com.tour.prevel.auth.repository.AuthRepository;
import com.tour.prevel.global.exception.NotFound;
import com.tour.prevel.plan.domain.*;
import com.tour.prevel.plan.dto.*;
import com.tour.prevel.plan.mapper.PlanMapper;
import com.tour.prevel.plan.repository.*;
import com.tour.prevel.plan.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;
    private final PlanImageRepository planImageRepository;
    private final PlanQueryRepository planQueryRepository;
    private final PlanMapper planMapper;

    private final ScheduleRepository scheduleRepository;
    private final ScheduleQueryRepository scheduleQueryRepository;

    private final AuthRepository authRepository;

    @Override
    public void createPlan(CreatePlanRequest request, String userId) {
        User user = authRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("사용자 정보가 없습니다."));
        planRepository.save(Plan.builder()
                .title(request.title())
                .adult(request.adult())
                .child(request.child())
                .location(request.location())
                .startDate(LocalDate.parse(request.startDate()))
                .endDate(LocalDate.parse(request.endDate()))
                .status(PlanStatus.REVISION)
                .planImage(getRandomPlanImage())
                .user(user)
                .build());
    }

    private PlanImage getRandomPlanImage() {
        List<PlanImage> planImages = planImageRepository.findAll();
        int randomIndex = (int) (Math.random() * planImages.size());
        return planImages.get(randomIndex);
    }

    @Override
    public List<ScheduleResponse> getScheduleListByDate(String id, LocalDate date) {
        return planMapper.toScheduleResponseList(scheduleQueryRepository.getScheduleListByDate(id, date));
    }

    @Override
    public List<RecommandPlanResponse> getRecommandPlanList(String userId) {
        List<Plan> recommandPlanList = planQueryRepository.getRecommandPlanList(userId);
        return planMapper.toRecomandPlanResponseList(recommandPlanList);
    }

    @Override
    public List<PlanHistoryResponse> getPlanHistoryList(String userId) {
        return planMapper.toPlanHistoryResponseList(planQueryRepository.getCompletedPlanList(userId));
    }

    @Override
    public PlanHistoryResponse getPlanRecord(Long id, String name) {
        Plan plan = planRepository.findById(id).orElseThrow(() -> new NotFound());
        return planMapper.toPlanHistoryResponse(plan);
    }

    @Override
    public int getPlanCount(String userId) {
        return planQueryRepository.getPlanCount(userId);
    }

    @Override
    public PlanDetailResponse getPlanDetail(String userId) {
        Plan activePlan = planQueryRepository.getActivePlan(userId);

        if (activePlan == null) {
            return null;
        }

        List<ScheduleResponse> scheduleList = planMapper.toScheduleResponseList(
                scheduleQueryRepository.getScheduleListByPlanId(activePlan.getId()));

        return PlanDetailResponse.builder()
                .planId(activePlan.getId())
                .startDate(activePlan.getStartDate().toString())
                .endDate(activePlan.getEndDate().toString())
                .schedules(scheduleList)
                .build();
    }

    @Override
    public void createScheudle(CreateScheduleRequest request) {
        Plan plan = planRepository.findById(Long.valueOf(request.getPlanId()))
                .orElseThrow(() -> new NotFound());

        if (plan.getEndDate().isBefore(LocalDate.parse(request.getDate())) ||
                plan.getStartDate().isAfter(LocalDate.parse(request.getDate()))) {
            throw new IllegalArgumentException("일정을 벗어난 날짜입니다.");
        }

        scheduleRepository.save(Schedule.builder()
                        .plan(plan)
                        .title(request.getTitle())
                        .description(request.getDescription())
                        .category(ScheduleCategory.valueOf(request.getCategory().equals("관광") ? "TOUR" : "FOOD"))
                        .thumbnail(request.getThumbnail())
                        .scheduleDate(LocalDate.parse(request.getDate()))
                        .scheduleOrder(scheduleQueryRepository.getLastOrder(Long.valueOf(request.getPlanId()), request.getDate()) + 1)
                .build());
    }
}
