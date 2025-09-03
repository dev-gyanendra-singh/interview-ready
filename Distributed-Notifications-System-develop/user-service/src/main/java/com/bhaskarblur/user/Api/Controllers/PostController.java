package com.bhaskarblur.user.Api.Controllers;

import com.bhaskarblur.user.Api.Dtos.CreatePostRequest;
import com.bhaskarblur.user.Api.Dtos.ApiStandardResponse;

import com.bhaskarblur.user.Models.PostModel;
import com.bhaskarblur.user.Services.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
@Validated
public class PostController {

    @Autowired
    public PostService service;

    @PostMapping
    public ResponseEntity<ApiStandardResponse> createPost(@Valid @RequestBody CreatePostRequest request) throws Exception {

        PostModel post = service.createPost(request);
        ApiStandardResponse response = new ApiStandardResponse(
                true,
                "Post created successfully",
                post
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
