package com.js0507dev.project1.wallet.entity;

import com.js0507dev.project1.common.entity.AbstractTimestampEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "currencies")
public class Currency extends AbstractTimestampEntity {
    @Id
    private String ticker;
    @Column
    private String name;
    @Column
    private String description;
}
