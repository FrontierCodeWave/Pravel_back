package com.tour.prevel.plan.service.impl;

import com.tour.prevel.auth.domain.User;
import com.tour.prevel.auth.repository.AuthRepository;
import com.tour.prevel.global.exception.NotFound;
import com.tour.prevel.plan.dto.*;
import com.tour.prevel.plan.domain.Plan;
import com.tour.prevel.plan.domain.PlanImage;
import com.tour.prevel.plan.domain.PlanStatus;
import com.tour.prevel.plan.mapper.PlanMapper;
import com.tour.prevel.plan.repository.PlanImageRepository;
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
    private final PlanImageRepository planImageRepository;
    private final PlanQueryRepository planQueryRepository;
    private final PlanMapper planMapper;

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
        return planMapper.toScheduleResponseList(planQueryRepository.getScheduleListByDate(id, date));
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
                planQueryRepository.getScheduleListByPlanId(activePlan.getId()));

        return PlanDetailResponse.builder()
                .planId(activePlan.getId())
                .schedules(scheduleList)
                .build();
    }
}
