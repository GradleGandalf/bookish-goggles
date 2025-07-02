package com.example.demo;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class RabbitMQConfig {

    @Value("${app.queue.name}")
    private String queueName;

    @Value("${app.exchange.name}")
    private String exchangeName;

    @Value("${app.routing-key}")
    private String routingKey;

    @Bean
    public Queue appQueue() {
        return new Queue(queueName, true);
    }

    @Bean
    public Exchange appExchange() {
        return ExchangeBuilder.topicExchange(exchangeName).durable(true).build();
    }

    @Bean
    public Binding appBinding(Queue appQueue, Exchange appExchange) {
        return BindingBuilder.bind(appQueue).to(appExchange).with(routingKey).noargs();
    }
}
