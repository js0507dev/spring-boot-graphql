package com.js0507dev.project1.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class AbstractTimestampEntity {
    @Column
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
