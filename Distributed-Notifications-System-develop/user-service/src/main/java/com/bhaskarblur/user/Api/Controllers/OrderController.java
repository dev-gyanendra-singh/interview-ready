package com.bhaskarblur.user.Api.Controllers;

import com.bhaskarblur.user.Api.Dtos.ApiStandardResponse;
import com.bhaskarblur.user.Api.Dtos.CreateOrderRequest;
import com.bhaskarblur.user.Api.Dtos.CreatePostRequest;
import com.bhaskarblur.user.Models.PostModel;
import com.bhaskarblur.user.Services.OrderService;
import com.bhaskarblur.user.Services.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@Validated
public class OrderController {

    @Autowired
    public OrderService service;

    @PostMapping
    public ResponseEntity<ApiStandardResponse> createOrder(@Valid @RequestBody CreateOrderRequest request) throws Exception {

        Object order = service.createOrder(request);
        ApiStandardResponse response = new ApiStandardResponse(
                true,
                "Order created successfully",
                order
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
