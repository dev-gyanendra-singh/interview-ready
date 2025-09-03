package com.bhaskarblur.order.Services;

import com.bhaskarblur.order.Kafka.IKafkaConsumers;
import com.bhaskarblur.order.Kafka.MessageConsumer;
import com.bhaskarblur.order.Models.OrderModel;
import com.bhaskarblur.order.Repositories.OrderRepository;
import com.bhaskarblur.order.Kafka.MessageProducer;
import com.google.gson.Gson;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService implements IKafkaConsumers {

    private final OrderRepository repository;
    private final MessageProducer kafkaMessageProducer;
    private final MessageConsumer kafkaMessageConsumer;
    private Gson gson;
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    public OrderService(OrderRepository repository, MessageProducer kafkaMessageProducer, MessageConsumer kafkaMessageConsumer) {
        this.repository = repository;
        this.kafkaMessageProducer = kafkaMessageProducer;
        this.kafkaMessageConsumer = kafkaMessageConsumer;
        kafkaMessageConsumer.subscribeToOrders(this);
    }

    @Autowired
    public void setGson(Gson gson) {
        this.gson = gson;
    }

    private void createOrder(String orderRequest) {
        try {
            OrderModel orderModel = gson.fromJson(orderRequest, OrderModel.class);

            logger.info("Received createOrder Request: {}", orderModel.getUserId());

            orderModel.setCreatedAt(new Date());
            orderModel.setStatus("CREATED");
            orderModel = repository.createOrder(orderModel);

            logger.info("Saved Order ID: {}", orderModel.getId());

            // Manually set each property of post to notificationPayload
            Map<String, Object> notificationPayload = new HashMap<>();
            notificationPayload.put("type", "ORDER");
            notificationPayload.put("userId", orderModel.getUserId());
            notificationPayload.put("title", "Order placed successfully");
            notificationPayload.put("description", "Your order has been placed at "+ orderModel.getCreatedAt().toString());

            // Convert notification payload to JSON String using Gson
            String notificationJsonString = gson.toJson(notificationPayload);
            logger.info("Sending notification to Kafka: {}", notificationJsonString);

            // 2. Send to Kafka Topic via MessageProducer
            kafkaMessageProducer.sendMessage("notification", notificationJsonString);

        } catch (Exception e) {
            throw new RuntimeException("Error creating order: " + e.getMessage(), e);
        }
    }


    public List<OrderModel> getOrdersByUserId(String userId) throws Exception {
        try {
            logger.info("Received getOrdersByUserId Request: {}", userId);

            return repository.findByUserId(userId);
        } catch (Exception e) {
            throw new RuntimeException("Error getting notifications: " + e.getMessage(), e);
        }
    }


    @Override
    public void Notify(String topic, String message) {
        try {
            createOrder(message);
        } catch (Exception e) {
            logger.error("Notification Service message: {} threw error: {}", topic, e.getMessage());
        }
    }

    @PreDestroy
    public void cleanup() {
        kafkaMessageConsumer.unSubscribeToOrders(this);
        logger.info("Unsubscribed from orders on service shutdown");
    }
}
