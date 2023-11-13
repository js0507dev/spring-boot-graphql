package com.js0507dev.order.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderPayloadDTO {
  private OrderDTO record;

  public static CreateOrderPayloadDTO fromOrderDTO(OrderDTO record) {
    return CreateOrderPayloadDTO
        .builder()
        .record(record)
        .build();
  }
}
