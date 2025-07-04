package com.example.demo

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class ProducerController(private val producer: RabbitMQProducer) {

    @PostMapping("/messages")
    fun publish(@RequestBody dto: MessageDto): ResponseEntity<Void> {
        producer.send(dto.content)
        return ResponseEntity.accepted().build()
    }
}
