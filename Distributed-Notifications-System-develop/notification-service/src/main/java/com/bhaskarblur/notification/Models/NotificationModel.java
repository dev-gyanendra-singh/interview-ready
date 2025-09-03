package com.bhaskarblur.notification.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.Date;
import java.util.UUID;

@Document(collection = "notifications")
public class NotificationModel {

    @MongoId(FieldType.OBJECT_ID)
    private String id;

    private String type;
    private String userId;
    private String title;
    private String description;
    private Date createdAt;

    public NotificationModel() {}
    public NotificationModel(String userId, String type, String title, String description) {
        this.id = UUID.randomUUID().toString(); // Generate a unique ID
        this.userId = userId;
        this.type = type;
        this.title = title;
        this.description = description;
        this.createdAt = new Date();
    }

    public NotificationModel(String id, String userId,String type, String title, String description, Date createdAt) {
        this.id = id != null ? id : UUID.randomUUID().toString(); // Use provided ID or generate one
        this.userId = userId;
        this.type = type;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt != null ? createdAt : new Date(); // Use provided date or set to now
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public NotificationModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
