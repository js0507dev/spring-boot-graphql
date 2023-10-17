package com.js0507dev.project1.order.service;

import com.js0507dev.project1.auth.util.AuthenticationFacade;
import com.js0507dev.project1.member.entity.Member;
import com.js0507dev.project1.member.service.MemberService;
import com.js0507dev.project1.order.dto.CreateOrderDTO;
import com.js0507dev.project1.order.entity.Order;
import com.js0507dev.project1.order.repository.OrderRepository;
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
  @Value("${application.rabbitmq.order-created.steps.register-order-book.routing-key}")
  private String registerOrderBookRoutingKey;
  @Value("${application.rabbitmq.order-created.exchange}")
  private String orderCreatedExchangeName;

  private final AuthenticationFacade authenticationFacade;
  private final MemberService memberService;

  private final RabbitTemplate rabbitTemplate;
  private final OrderRepository orderRepository;

  public Order createOrder(CreateOrderDTO dto) {
    Authentication authentication = authenticationFacade.getAuthentication();
//    Member member = memberService.findByEmail(authentication.getName());
    Member member = memberService.findAll().get(0);
    Order createOption = dto.toEntity(member);
    Order created = orderRepository.save(createOption);

    rabbitTemplate.convertAndSend(orderCreatedExchangeName, registerOrderBookRoutingKey, dto);

    return created;
  }
}
