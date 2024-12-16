package com.kafka.experiment.handler;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.DltStrategy;
import org.springframework.kafka.retrytopic.FixedDelayStrategy;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @RetryableTopic(
            attempts = "4",
            fixedDelayTopicStrategy = FixedDelayStrategy.SINGLE_TOPIC,
            backoff = @Backoff(delayExpression = "1000", multiplierExpression = "0")
    )
    @KafkaListener(topics = {"test-topic-sp"})
    private void consumeKafkaRecord(ConsumerRecord<String, String> consumerRecord,
                                    Acknowledgment acknowledgment,
                                    @Header(KafkaHeaders.RECEIVED_TOPIC) String receivedTopic) {
        System.out.println("received message : " + consumerRecord.value());
        acknowledgment.acknowledge();
        if (1 == 1)
            throw new RuntimeException();
    }

    /*@KafkaListener(topics = {"custom-retry"})
    private void consumeRetry(ConsumerRecord<String, String> consumerRecord, Acknowledgment acknowledgment) {
        System.out.println("received message : " + consumerRecord.value());
        if (1 == 1)
            throw new RuntimeException();
    }*/


    @KafkaListener(topics = {"custom-dlt"})
    private void consumeDlt(ConsumerRecord<String, String> consumerRecord, Acknowledgment acknowledgment) {
        System.out.println("received message : " + consumerRecord.value());
    }

}
