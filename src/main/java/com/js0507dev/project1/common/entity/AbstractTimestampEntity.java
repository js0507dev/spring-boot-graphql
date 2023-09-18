package com.js0507dev.project1.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class AbstractTimestampEntity {
    @Column
    @CreatedDate
    private LocalDateTime createdAt;
    @Column
    @LastModifiedBy
    private LocalDateTime updatedAt;
}
