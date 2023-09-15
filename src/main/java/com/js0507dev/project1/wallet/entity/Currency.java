package com.js0507dev.project1.wallet.entity;

import com.js0507dev.project1.common.entity.AbstractTimestampEntity;
import com.js0507dev.project1.wallet.enums.Ticker;
import jakarta.persistence.*;
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
    @Enumerated(EnumType.STRING)
    private Ticker ticker;
    @Column
    private String name;
    @Column
    private String description;
}
