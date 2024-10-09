package com.tour.prevel.quest.mapper;

import com.tour.prevel.quest.domain.Quest;
import com.tour.prevel.quest.dto.QuestResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestMapper {

    List<QuestResponse> toQuestResponses(List<Quest> quest);
}
