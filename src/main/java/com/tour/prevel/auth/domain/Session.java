package com.tour.prevel.auth.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Table
@Entity
public class Session {

    @Id
    private String id;

    private LocalDateTime expiresAt;

    @Column(length = 1000)
    private String token;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;
}
