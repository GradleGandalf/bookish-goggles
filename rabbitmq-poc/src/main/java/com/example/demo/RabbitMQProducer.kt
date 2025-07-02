package com.example.demo

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class RabbitMQProducer(
    private val rabbitTemplate: RabbitTemplate,
    @Value("\${app.exchange.name}") private val exchange: String,
    @Value("\${app.routing-key}") private val routingKey: String
) {
    fun send(content: String) {
        rabbitTemplate.convertAndSend(exchange, routingKey, content)
    }
}
