package com.bhaskarblur.notification.Services;

import com.bhaskarblur.notification.Kafka.IKafkaConsumers;
import com.bhaskarblur.notification.Kafka.MessageConsumer;
import com.bhaskarblur.notification.Models.NotificationModel;
import com.bhaskarblur.notification.Repositories.NotificationRepository;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.util.Date;
import java.util.List;

public class NotificationService implements IKafkaConsumers {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);
    private final NotificationRepository repository;
    private Gson gson;
    private final MessageConsumer messageConsumer;

    @Autowired
    public void setGson(Gson gson) {
        this.gson = gson;
    }

    @Autowired
    public NotificationService(NotificationRepository repository, MessageConsumer messageConsumer) {
        this.repository = repository;
        this.messageConsumer = messageConsumer;

        // Subscribe to Msg Consumers
        messageConsumer.subscribeToNotifications(this);
    }

    private void saveNotification(String notificationBody) throws Exception {
        try {
            NotificationModel notificationModel = gson.fromJson(notificationBody, NotificationModel.class);

            logger.info("Received saveNotification Request: {}", notificationModel.getTitle());

            notificationModel.setCreatedAt(new Date());
            notificationModel = repository.saveNotification(notificationModel);

            logger.info("Saved Notification ID: {}", notificationModel.getId());

        } catch (Exception e) {
            throw new RuntimeException("Error saving notification: " + e.getMessage(), e);
        }
    }

    public List<NotificationModel> getNotificationsByUserId(String userId) throws Exception {
        try {
            logger.info("Received getNotificationsByUserId Request: {}", userId);

            return repository.findByUserId(userId);
        } catch (Exception e) {
            throw new RuntimeException("Error getting notifications: " + e.getMessage(), e);
        }
    }

    @Override
    public void Notify(String topic, String message) {
        try {
            saveNotification(message);
        } catch (Exception e) {
            logger.error("Notification Service message: {} threw error: {}", topic, e.getMessage());
        }
    }

    @PreDestroy
    public void cleanup() {
        messageConsumer.unSubscribeToNotifications(this);
        logger.info("Unsubscribed from notifications on service shutdown");
    }
}
