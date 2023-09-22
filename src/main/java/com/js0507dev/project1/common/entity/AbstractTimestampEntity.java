package com.js0507dev.project1.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Builder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class AbstractTimestampEntity {
    @Column
    @CreatedDate
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column
    @LastModifiedBy
    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();
}
