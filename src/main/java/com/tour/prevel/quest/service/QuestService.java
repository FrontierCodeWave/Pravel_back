package com.tour.prevel.quest.service;

import com.tour.prevel.quest.controller.StartQuestRequest;
import com.tour.prevel.quest.dto.Position;
import com.tour.prevel.quest.dto.QuestResponse;

import java.util.List;

public interface QuestService {
    List<QuestResponse> getQuestListByLocation(Position position, String userId);
    void startQuest(Long questId, StartQuestRequest request, String userId);
}
