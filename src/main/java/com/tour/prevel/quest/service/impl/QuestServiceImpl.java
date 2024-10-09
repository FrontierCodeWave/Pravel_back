package com.tour.prevel.quest.service.impl;

import com.tour.prevel.auth.domain.User;
import com.tour.prevel.auth.repository.AuthRepository;
import com.tour.prevel.plan.dto.CreateScheduleRequest;
import com.tour.prevel.plan.service.PlanService;
import com.tour.prevel.quest.controller.StartQuestRequest;
import com.tour.prevel.quest.domain.Quest;
import com.tour.prevel.quest.domain.UserQuest;
import com.tour.prevel.quest.dto.Position;
import com.tour.prevel.quest.dto.QuestResponse;
import com.tour.prevel.quest.mapper.QuestMapper;
import com.tour.prevel.quest.repository.QuestQueryRepository;
import com.tour.prevel.quest.repository.QuestRepository;
import com.tour.prevel.quest.repository.UserQuestRepository;
import com.tour.prevel.quest.service.QuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestServiceImpl implements QuestService {

    private final QuestQueryRepository questQueryRepository;
    private final QuestRepository questRepository;
    private final QuestMapper questMapper;

    private final UserQuestRepository userQuestRepository;

    private final AuthRepository authRepository;

    private final PlanService planService;

    @Override
    public List<QuestResponse> getQuestListByLocation(Position position, String userId) {
        List<Quest> questListByLocation = questQueryRepository.getQuestListByLocation(position, userId);
        return questMapper.toQuestResponses(questListByLocation);
    }

    @Override
    public void startQuest(Long questId, StartQuestRequest request, String userId) {
        Quest quest = questRepository.findById(questId)
                .orElseThrow(() -> new IllegalArgumentException("Quest not found"));

        User user = authRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        userQuestRepository.save(UserQuest.builder()
                .quest(quest)
                .user(user)
                .build());

        planService.createScheudle(CreateScheduleRequest.builder()
                        .planId(request.getPlanId())
                        .title(quest.getTitle())
                        .category("TOUR")
                        .date(request.getDate())
                .build());
    }
}
