package com.bhaskarblur.order.Di;

import com.bhaskarblur.order.Kafka.MessageConsumer;
import com.bhaskarblur.order.Kafka.MessageProducer;
import com.bhaskarblur.order.Repositories.OrderRepository;
import com.bhaskarblur.order.Services.OrderService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppModules {
    private static final Logger logger = LoggerFactory.getLogger(AppModules.class);

    @Bean
    public Gson injectGson() {
        return new Gson();
    }

    @Bean
    public OrderService injectOrderService(OrderRepository repository, MessageProducer messageProducer, MessageConsumer messageConsumer) {
        logger.info("🚀 Initialized Order Service");
        return new OrderService(repository, messageProducer, messageConsumer);
    }
}
