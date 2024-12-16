package com.kafka.experiment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.retrytopic.DestinationTopic;
import org.springframework.kafka.retrytopic.RetryTopicNamesProviderFactory;
import org.springframework.kafka.retrytopic.SuffixingRetryTopicNamesProviderFactory;

@Configuration
public class KafkaRetryTopicConfig {

    @Bean
    public RetryTopicNamesProviderFactory customRetryTopicComponentFactory() {

        return new RetryTopicNamesProviderFactory() {
            @Override
            public RetryTopicNamesProvider createRetryTopicNamesProvider(DestinationTopic.Properties properties) {
                return new SuffixingRetryTopicNamesProviderFactory.SuffixingRetryTopicNamesProvider(properties) {

                    @Override
                    public String getTopicName(String topic) {
                        if (properties.isMainEndpoint()) {
                            return topic;
                        } else if (properties.isDltTopic()) {
                            return "custom-dlt";
                        }
                        return "custom-retry";
                    }
                };
            }
        };
    }
}
