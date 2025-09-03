package com.bhaskarblur.notification.Api.Controllers;

import com.bhaskarblur.notification.Api.Dtos.ApiStandardResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

@RestController
public class DefaultController implements ErrorController {

    private static final Logger logger = LoggerFactory.getLogger(DefaultController.class);

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiStandardResponse> healthCheck() {
        return ResponseEntity.ok(new ApiStandardResponse(true, "Service running well!", null));
    }

    @RequestMapping("/error")
    public ResponseEntity<ApiStandardResponse> handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("jakarta.servlet.error.status_code");

        if (statusCode != null) {
            HttpStatus status = HttpStatus.resolve(statusCode);

            // Log the received status code
            logger.info("Error encountered with status code: {}", statusCode);

            String message = (String) request.getAttribute("jakarta.servlet.error.message");
            if (status == HttpStatus.METHOD_NOT_ALLOWED) {
                logger.warn("405 Method Not Allowed - Method does not exist");
                ApiStandardResponse response = new ApiStandardResponse(
                        false,
                        "405 Method Not Allowed - Method does not exist",
                        null
                );
                return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(response);
            }

            // Use custom message if provided, otherwise default to reason phrase
            String errorMessage;
            if ((message != null)) {
                errorMessage = message;
            } else {
                assert status != null;
                errorMessage = status.getReasonPhrase();
            }
            ApiStandardResponse response = new ApiStandardResponse(
                    false,
                    errorMessage,
                    null
            );
            return ResponseEntity.status(statusCode).body(response);
        }

        // Handle unexpected internal server error
        logger.error("Unexpected error occurred - returning 500 Internal Server Error");
        ApiStandardResponse response = new ApiStandardResponse(
                false,
                "An unexpected error occurred",
                null
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
