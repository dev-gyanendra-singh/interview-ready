package com.bhaskarblur.order;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class OrderApplication {

	private static final Logger logger = LoggerFactory.getLogger(com.bhaskarblur.order.OrderApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(com.bhaskarblur.order.OrderApplication.class, args);
	}

	// Log the port after application start
	public OrderApplication(@Value("${server.port}") int port) {
		logger.info("✨ Started Order service on port: {}", port);
	}
}
