package com.tour.prevel.quest.repository;

import com.tour.prevel.quest.domain.UserQuest;
import com.tour.prevel.quest.domain.UserQuestPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserQuestRepository extends JpaRepository<UserQuest, UserQuestPK> {
}
