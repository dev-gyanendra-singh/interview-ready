package com.bhaskarblur.order.Kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MessageConsumer {

    @Value("${spring.kafka.topic}")
    private String topic;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    private static final Logger logger = LoggerFactory.getLogger(MessageConsumer.class);

    // List to hold dynamic consumers
    private final List<IKafkaConsumers> consumers = new ArrayList<>();

    // Method to add new consumer
    public void subscribeToOrders(IKafkaConsumers consumer) {
        logger.info("👋 New Consumer added to consumers topic: {} , groupId {}", topic, groupId);
        consumers.add(consumer);
    }

    public void unSubscribeToOrders(IKafkaConsumers consumer) {
        logger.info("👋 Removing Consumer from consumers topic: {} , groupId {}", topic, groupId);
        consumers.remove(consumer);
    }
    @KafkaListener(topics = "${spring.kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(String message) {
        logger.info("Received message on topic {}: {}", topic, message);
        consumers.forEach(consumer -> {
            try {
                consumer.Notify(topic, message);
            } catch (Exception e) {
                logger.error("Consumer {} failed to process message: {}", consumer.getClass().getSimpleName(), e.getMessage(), e);
            }
        });
    }
}
