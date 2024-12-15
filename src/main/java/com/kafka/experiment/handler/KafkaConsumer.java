package com.kafka.experiment.handler;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = {"test-topic"})
    private void consumeKafkaRecord(ConsumerRecord<String, String> consumerRecord) {
        System.out.println("received message : " + consumerRecord.value());
    }

}
