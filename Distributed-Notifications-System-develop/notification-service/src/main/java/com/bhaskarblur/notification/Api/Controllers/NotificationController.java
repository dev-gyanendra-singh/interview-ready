package com.bhaskarblur.notification.Api.Controllers;

import com.bhaskarblur.notification.Api.Dtos.ApiStandardResponse;
import com.bhaskarblur.notification.Models.NotificationModel;
import com.bhaskarblur.notification.Services.NotificationService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/{userId}/notifications")
@Validated
public class NotificationController {

    private final NotificationService service;

    @Autowired
    public NotificationController(NotificationService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ApiStandardResponse> getUserNotifications(
            @NotNull @PathVariable @NotBlank(message = "User ID is required")
            @Size(min = 3, max = 20, message = "User ID must be between 3 and 20 characters") String userId) throws Exception {

        List<NotificationModel> notifications = service.getNotificationsByUserId(userId);

        ApiStandardResponse response = new ApiStandardResponse(
                true, "Notifications fetched!", notifications
        );
        return ResponseEntity.ok().body(response);
    }
}
