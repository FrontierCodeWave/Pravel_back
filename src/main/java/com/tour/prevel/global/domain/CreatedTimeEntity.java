package com.tour.prevel.global.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class CreatedTimeEntity {

    @CreatedDate
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Column(name = "CREATE_DT", nullable = false, updatable = false)
    private LocalDateTime createdTime;
}

