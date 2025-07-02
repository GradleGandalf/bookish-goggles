package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProducerController {

    private final RabbitMQProducer producer;

    public ProducerController(RabbitMQProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/messages")
    public ResponseEntity<Void> publish(@RequestBody MessageDto dto) {
        producer.send(dto.content());
        return ResponseEntity.accepted().build();
    }
}
