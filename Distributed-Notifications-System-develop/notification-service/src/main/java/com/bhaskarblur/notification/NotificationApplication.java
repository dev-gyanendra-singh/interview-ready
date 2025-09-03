package com.bhaskarblur.notification;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class NotificationApplication {

	private static final Logger logger = LoggerFactory.getLogger(NotificationApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(NotificationApplication.class, args);
	}

	// Log the port after application start
	public NotificationApplication(@Value("${server.port}") int port) {
		logger.info("✨ Started User service on port: {}", port);
	}
}
