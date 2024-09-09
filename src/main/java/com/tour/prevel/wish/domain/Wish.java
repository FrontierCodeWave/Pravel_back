package com.tour.prevel.wish.domain;

import com.tour.prevel.auth.domain.User;
import com.tour.prevel.tourapi.domain.ContentCategory;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Table(name = "TB_WISH")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Wish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    private String contentId;

    @Enumerated(EnumType.STRING)
    private ContentCategory category;
}
