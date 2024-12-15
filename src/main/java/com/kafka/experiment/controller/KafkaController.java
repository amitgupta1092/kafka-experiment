package com.kafka.experiment.controller;

import com.kafka.experiment.handler.KafkaProducer;
import com.kafka.experiment.model.KafkaPublishRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    private final KafkaProducer kafkaProducer;

    public KafkaController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping
    public ResponseEntity<Void> publishMessage(@RequestBody KafkaPublishRequest publishRequest) {

        kafkaProducer.publish(publishRequest.getTopicName(),
                publishRequest.getKey(),
                publishRequest.getValue());

        return ResponseEntity.ok().body(null);
    }
}
