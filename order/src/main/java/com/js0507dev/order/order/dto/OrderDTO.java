package com.js0507dev.order.order.dto;

import com.js0507dev.order.order.entity.Order;
import com.js0507dev.order.order.enums.OrderType;
import com.js0507dev.order.order.enums.TradeType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDTO {
  private Long id;
  private TradeType tradeType;
  private OrderType orderType;
  private Long unitPrice;
  private Long totalVolume;
  private Long tradeVolume;
  private String createdAt;
  private String updatedAt;

  public static OrderDTO fromEntity(Order entity) {
    return OrderDTO
        .builder()
        .id(entity.getId())
        .tradeType(entity.getTradeType())
        .orderType(entity.getOrderType())
        .unitPrice(entity.getUnitPrice())
        .totalVolume(entity.getTotalVolume())
        .tradeVolume(entity.getTradeVolume())
        .createdAt(entity.getCreatedAt().toString())
        .updatedAt(entity.getUpdatedAt().toString())
        .build();
  }
}
