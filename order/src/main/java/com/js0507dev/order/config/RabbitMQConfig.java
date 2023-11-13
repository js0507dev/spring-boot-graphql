package com.js0507dev.order.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitMQConfig {
//  @Value("${application.rabbitmq.order-created.steps.register-order-book.queue}")
//  private String registerOrderBookQueueName;
//  @Value("${application.rabbitmq.order-created.steps.register-order-book.routing-key}")
//  private String registerOrderBookRoutingKey;
//  @Value("${application.rabbitmq.order-created.exchange}")
//  private String orderCreatedExchangeName;
//
//  private final CachingConnectionFactory connectionFactory;

//  @Bean
//  Queue queue() {
//    return new Queue(registerOrderBookQueueName);
//  }

//  @Bean
//  Declarables createPostOrderRegistrationSchema() {
//    return new Declarables(
//        new TopicExchange(orderCreatedExchangeName),
//        new Queue(registerOrderBookQueueName),
//        new Binding(registerOrderBookQueueName, Binding.DestinationType.QUEUE, orderCreatedExchangeName,
//            registerOrderBookRoutingKey, null));
//  }
//
//  @Bean
//  Jackson2JsonMessageConverter jsonMessageConverter() {
//    return new Jackson2JsonMessageConverter();
//  }
//
//  @Bean
//  RabbitTemplate rabbitTemplate(Jackson2JsonMessageConverter converter) {
//    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//    rabbitTemplate.setMessageConverter(converter);
//    return rabbitTemplate;
//  }

//  @Bean
//  MessageListenerContainer messageListenerContainer(Queue queue) {
//    SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
//    simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
//    simpleMessageListenerContainer.setQueues(queue);
//    simpleMessageListenerContainer.setMessageListener();
//  }
}
