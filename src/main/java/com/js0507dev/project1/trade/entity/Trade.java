package com.js0507dev.project1.trade.entity;

import com.js0507dev.project1.common.entity.AbstractTimestampEntity;
import com.js0507dev.project1.member.entity.Member;
import com.js0507dev.project1.order.entity.Order;
import com.js0507dev.project1.order.enums.OrderType;
import com.js0507dev.project1.order.enums.TradeType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "trades")
public class Trade extends AbstractTimestampEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column
    private TradeType tradeType;
    @Column
    private Long tradeUnitPrice;
    @Column
    private Long tradeVolume;
    @Column
    private Long tradeAmount;
    @Column(name = "maker_member_id", insertable = false, updatable = false)
    private Long makerMemberId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maker_member_id", referencedColumnName = "id")
    private Member makerMember;
    @Column(name = "maker_order_id", insertable = false, updatable = false)
    private Long makerOrderId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maker_order_id", referencedColumnName = "id")
    private Order makerOrder;
    @Column(name = "taker_member_id", insertable = false, updatable = false)
    private Long takerMemberId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taker_member_id", referencedColumnName = "id")
    private Member takerMember;
    @Column(name = "taker_order_id", insertable = false, updatable = false)
    private Long takerOrderId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taker_order_id", referencedColumnName = "id")
    private Order takerOrder;
}
