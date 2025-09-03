package com.bhaskarblur.user.Api.Dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CreatePostRequest {

    @NotNull(message = "User id is required")
    private String user_id;

    @NotNull(message = "Title is required")
    @Size(min = 5, max = 100, message = "Title must be between 5 and 100 characters")
    private String title;

    @NotEmpty(message = "Content is required")
    private String content;

    @Size(max = 250, message = "Description cannot exceed 250 characters")
    private String description;

    public CreatePostRequest() {}

    public CreatePostRequest(String user_id, String title, String content, String description) {
        this.user_id = user_id;
        this.title = title;
        this.content = content;
        this.description = description;

    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
