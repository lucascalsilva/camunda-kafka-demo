package org.camunda.bpm.demo.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.camunda.bpm.demo.dto.CamundaMessageDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value(value = "${kafka.bootstrap-address}")
    private String bootstrapAddress;

    @Value(value = "${kafka.trusted-packages}")
    private String trustedPackage;

    @Bean
    public ProducerFactory<String, CamundaMessageDto> producerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);
        props.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        props.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JsonSerializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, trustedPackage);
        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, CamundaMessageDto> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}