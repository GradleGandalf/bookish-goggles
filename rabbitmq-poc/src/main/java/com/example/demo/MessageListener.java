package com.example.demo;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    private final MessageRepository repository;

    public MessageListener(MessageRepository repository) {
        this.repository = repository;
    }

    @RabbitListener(queues = "${app.queue.name}")
    public void receive(String content) {
        repository.save(new MessageEntity(content));
    }
}
