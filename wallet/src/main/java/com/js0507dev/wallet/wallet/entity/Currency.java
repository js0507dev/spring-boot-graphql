package com.js0507dev.wallet.wallet.entity;

import com.js0507dev.wallet.common.entity.AbstractTimestampEntity;
import com.js0507dev.wallet.wallet.enums.Ticker;
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
