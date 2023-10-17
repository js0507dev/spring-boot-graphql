package com.js0507dev.project1.order.dto;

import com.js0507dev.project1.member.entity.Member;
import com.js0507dev.project1.order.entity.Order;
import com.js0507dev.project1.order.enums.OrderPosition;
import com.js0507dev.project1.order.enums.OrderType;
import com.js0507dev.project1.order.enums.TradeType;
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
