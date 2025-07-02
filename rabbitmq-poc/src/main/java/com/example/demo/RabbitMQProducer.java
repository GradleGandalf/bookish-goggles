package com.example.demo;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQProducer {

    private final RabbitTemplate rabbitTemplate;
    private final String exchange;
    private final String routingKey;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate,
                            @Value("${app.exchange.name}") String exchange,
                            @Value("${app.routing-key}") String routingKey) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
        this.routingKey = routingKey;
    }

    public void send(String content) {
        rabbitTemplate.convertAndSend(exchange, routingKey, content);
    }
}
