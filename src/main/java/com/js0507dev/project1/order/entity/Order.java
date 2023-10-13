package com.js0507dev.project1.order.entity;

import com.js0507dev.project1.common.entity.AbstractTimestampEntity;
import com.js0507dev.project1.member.entity.Member;
import com.js0507dev.project1.order.enums.OrderType;
import com.js0507dev.project1.order.enums.TradeType;
import com.js0507dev.project1.wallet.enums.Ticker;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order extends AbstractTimestampEntity {
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
    private TradeType tradeType;
    @Enumerated(EnumType.STRING)
    @Column
    private OrderType orderType;
    @Column
    private Long totalVolume;
    @Column
    @Builder.Default
    private Long tradeVolume = 0L;
    @Column(name = "cancel_order_id", insertable = false, updatable = false)
    private Long cancelOrderId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cancel_order_id", referencedColumnName = "id")
    private Order cancelOrder;
}
