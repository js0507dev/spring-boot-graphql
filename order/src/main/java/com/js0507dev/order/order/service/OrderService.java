package com.js0507dev.order.order.service;

import com.js0507dev.order.auth.util.AuthenticationFacade;
import com.js0507dev.order.member.entity.Member;
import com.js0507dev.order.member.service.MemberService;
import com.js0507dev.order.order.dto.CreateOrderDTO;
import com.js0507dev.order.order.entity.Order;
import com.js0507dev.order.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
//  @Value("${application.rabbitmq.order-created.steps.register-order-book.routing-key}")
//  private String registerOrderBookRoutingKey;
//  @Value("${application.rabbitmq.order-created.exchange}")
//  private String orderCreatedExchangeName;

  private final AuthenticationFacade authenticationFacade;
  private final MemberService memberService;

  private final RabbitTemplate rabbitTemplate;
  private final OrderRepository orderRepository;

  public Order createOrder(CreateOrderDTO dto) {
    Authentication authentication = authenticationFacade.getAuthentication();
    Member member = memberService.findByEmail(authentication.getName());
    Order createOption = dto.toEntity(member);
    Order created = orderRepository.save(createOption);

//    rabbitTemplate.convertAndSend(orderCreatedExchangeName, registerOrderBookRoutingKey, dto);

    return created;
  }
}
