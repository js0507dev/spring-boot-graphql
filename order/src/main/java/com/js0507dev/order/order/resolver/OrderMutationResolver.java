package com.js0507dev.order.order.resolver;

import com.js0507dev.order.order.dto.CreateOrderDTO;
import com.js0507dev.order.order.dto.CreateOrderPayloadDTO;
import com.js0507dev.order.order.dto.OrderDTO;
import com.js0507dev.order.order.entity.Order;
import com.js0507dev.order.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class OrderMutationResolver {
  private final OrderService orderService;

  @MutationMapping
//  @PreAuthorize("isAuthenticated()")
  public CreateOrderPayloadDTO createOrder(@Argument("dto") CreateOrderDTO dto) {
    Order created = orderService.createOrder(dto);
    return CreateOrderPayloadDTO.fromOrderDTO(OrderDTO.fromEntity(created));
  }
}
