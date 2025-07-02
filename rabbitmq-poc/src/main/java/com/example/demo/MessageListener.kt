package com.example.demo

import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class MessageListener(private val repository: MessageRepository) {
    @RabbitListener(queues = ["\${app.queue.name}"])
    fun receive(content: String) {
        repository.save(MessageEntity(content = content))
    }
}
