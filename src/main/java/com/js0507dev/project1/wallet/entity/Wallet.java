package com.js0507dev.project1.wallet.entity;

import com.js0507dev.project1.common.entity.AbstractTimestampEntity;
import com.js0507dev.project1.member.entity.Member;
import com.js0507dev.project1.wallet.enums.Ticker;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "wallets")
public class Wallet extends AbstractTimestampEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "member_id", insertable = false, updatable = false)
    private Long memberId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Member member;
    @Enumerated(EnumType.STRING)
    @Column
    private Ticker ticker;
    @Column
    private Long totalBalance;
    @Column
    private Long availableBalance;
    @Column
    private Long lockedAmount;
}
