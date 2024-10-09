package com.tour.prevel.quest.domain;

import com.tour.prevel.auth.domain.User;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "TB_USER_QUEST")
@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@IdClass(UserQuestPK.class)
public class UserQuest {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    private Quest quest;
}
