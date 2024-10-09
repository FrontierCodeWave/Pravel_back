package com.tour.prevel.quest.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tour.prevel.quest.domain.Quest;
import com.tour.prevel.quest.dto.Position;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import static com.tour.prevel.quest.domain.QQuest.quest;
import static com.tour.prevel.quest.domain.QUserQuest.userQuest;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<Quest> getQuestListByLocation(Position position, String userId) {
        return queryFactory.select(quest)
                .from(quest)
                .leftJoin(userQuest).on(quest.eq(userQuest.quest))
                .where(quest.x.between(position.getX() - 0.01, position.getX() + 0.01)
                        .and(quest.y.between(position.getY() - 0.01, position.getY() + 0.01))
                        .and(
                                userQuest.user.email.ne(userId).or(userQuest.isNull())))
                .fetch()
                .stream().toList();
    }
}
