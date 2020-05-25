package org.camunda.bpm.demo.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Value(value = "${kafka.bootstrap-address}")
    private String bootstrapAddress;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic startProcessMessageTopic() {
        return new NewTopic("start-process-message-topic", 1, (short) 1);
    }

    @Bean
    public NewTopic intermediateMessageTopic() {
        return new NewTopic("intermediate-message-topic", 1, (short) 1);
    }

    @Bean
    public NewTopic serviceTaskMessageTopic() {
        return new NewTopic("service-task-message-topic", 1, (short) 1);
    }
}