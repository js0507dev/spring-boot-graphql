package com.js0507dev.order.order.dto;

import com.js0507dev.order.member.entity.Member;
import com.js0507dev.order.order.entity.Order;
import com.js0507dev.order.order.enums.OrderType;
import com.js0507dev.order.order.enums.TradeType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateOrderDTO {
  private TradeType orderPosition;
  private OrderType orderType;
  private Long quantity;
  private Long unitPrice;

  public Order toEntity(Member member) {
    return Order
        .builder()
        .member(member)
        .tradeType(orderPosition)
        .orderType(orderType)
        .unitPrice(unitPrice)
        .totalVolume(quantity)
        .build();
  }
}
