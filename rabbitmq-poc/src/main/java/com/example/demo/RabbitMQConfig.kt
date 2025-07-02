package com.example.demo

import org.springframework.amqp.core.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQConfig(
    @Value("\${app.queue.name}")
    private val queueName: String,
    @Value("\${app.exchange.name}")
    private val exchangeName: String,
    @Value("\${app.routing-key}")
    private val routingKey: String
) {

    @Bean
    fun appQueue(): Queue = Queue(queueName, true)

    @Bean
    fun appExchange(): Exchange = ExchangeBuilder.topicExchange(exchangeName).durable(true).build()

    @Bean
    fun appBinding(appQueue: Queue, appExchange: Exchange): Binding =
        BindingBuilder.bind(appQueue).to(appExchange).with(routingKey).noargs()
}
