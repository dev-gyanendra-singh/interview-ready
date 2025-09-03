package com.bhaskarblur.user.Repositories;

import com.bhaskarblur.user.Models.PostModel;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

public class PostRepository {

    public PostRepository() {}

    public PostModel createPost(@NotNull PostModel post) {
        // Mocked Database operation
        return post.setId("uuid-postID");
    }
}
