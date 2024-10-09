package com.tour.prevel.quest.domain;

import com.tour.prevel.auth.domain.User;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
public class UserQuestPK implements Serializable {
    private User user;
    private Quest quest;
}
