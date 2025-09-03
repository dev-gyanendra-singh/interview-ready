package com.bhaskarblur.user.Services;

import com.bhaskarblur.user.Api.Dtos.CreateOrderRequest;
import com.bhaskarblur.user.Api.Dtos.CreatePostRequest;
import com.bhaskarblur.user.Kafka.MessageProducer;
import com.bhaskarblur.user.Models.PostModel;
import com.bhaskarblur.user.Repositories.PostRepository;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

public class OrderService {

    private final MessageProducer kafkaMessageProducer;
    private Gson gson;
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);


    @Autowired
    public OrderService(MessageProducer kafkaMessageProducer) {
        this.kafkaMessageProducer = kafkaMessageProducer;
    }

    @Autowired
    public void SetGson(Gson gson) {
        this.gson = gson;
    }

    public Object createOrder(CreateOrderRequest orderRequest) {
        try {
            logger.info("Creating order for user: {}", orderRequest.getUser_id());
            logger.info("Order details: items={}", orderRequest.getItems());

            // Check if the user ID is invalid
            if (orderRequest.getUser_id().contains("fake")) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        String.format("%s is a fake user, use a real one", orderRequest.getUser_id())
                );
            }

            // Prepare the order payload for Kafka message
            Map<String, Object> orderPayload = new HashMap<>();
            orderPayload.put("userId", orderRequest.getUser_id());
            orderPayload.put("storeId", orderRequest.getStore_id());
            orderPayload.put("items", orderRequest.getItems());

            // Convert payload to JSON string using Gson
            String orderJsonString = gson.toJson(orderPayload);
            logger.info("Sending order to Kafka: {}", orderJsonString);

            // Send the JSON payload to Kafka
            kafkaMessageProducer.sendMessage("order", orderJsonString);

            // Return the order payload or acknowledgment as confirmation
            return orderRequest;

        } catch (Exception e) {
            throw new RuntimeException("Error creating order: " + e.getMessage(), e);
        }
    }
}