package com.js0507dev.order.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.LocalDateTime;

@Getter
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
