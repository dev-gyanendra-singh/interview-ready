package com.bhaskarblur.user.Models;

import java.util.UUID;

public class PostModel {

    private String id;
    private String userId;
    private String title;
    private String content;
    private String description;

    // Constructor
    public PostModel() {}

    public PostModel(String id, String userId, String title, String content, String description) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.description = description;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public PostModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public PostModel setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public PostModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public PostModel setContent(String content) {
        this.content = content;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public PostModel setDescription(String description) {
        this.description = description;
        return this;
    }
}
