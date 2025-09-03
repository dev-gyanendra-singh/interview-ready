package com.bhaskarblur.notification.Repositories;

import com.bhaskarblur.notification.Models.NotificationModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends MongoRepository<NotificationModel, String> {

    List<NotificationModel> findByUserId(String userId);

    default NotificationModel saveNotification(NotificationModel notificationModel) {
        return save(notificationModel); // `save` is provided by MongoRepository
    }

    default List<NotificationModel> getNotificationsByUserId(String userId) {
        return findByUserId(userId);
    }
}
