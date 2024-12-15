package com.kafka.experiment.model;

import lombok.Data;

@Data
public class KafkaPublishRequest {
    private String topicName;
    private String key;
    private String value;
}
