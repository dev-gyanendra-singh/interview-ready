package com.bhaskarblur.order.Api.Controllers;

import com.bhaskarblur.order.Api.Dtos.ApiStandardResponse;
import com.bhaskarblur.order.Models.OrderModel;
import com.bhaskarblur.order.Services.OrderService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/{userId}/orders")
@Validated
public class OrderController {

    private final OrderService service;

    @Autowired
    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ApiStandardResponse> getUserOrders(
            @NotNull @PathVariable @NotBlank(message = "User ID is required")
            @Size(min = 3, max = 20, message = "User ID must be between 3 and 20 characters") String userId) throws Exception {

        List<OrderModel> orders = service.getOrdersByUserId(userId);

        ApiStandardResponse response = new ApiStandardResponse(
                true, "Orders fetched successfully!", orders
        );
        return ResponseEntity.ok().body(response);
    }
}
