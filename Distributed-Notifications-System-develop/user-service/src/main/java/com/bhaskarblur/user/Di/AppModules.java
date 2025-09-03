package com.bhaskarblur.user.Di;

import com.bhaskarblur.user.Kafka.MessageProducer;
import com.bhaskarblur.user.Repositories.PostRepository;
import com.bhaskarblur.user.Services.OrderService;
import com.bhaskarblur.user.Services.PostService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppModules {
    private static final Logger logger = LoggerFactory.getLogger(AppModules.class);
    @Bean
    public PostRepository injectPostRepository() {
        logger.info("🚀 Initialized Post Repository");
        return new PostRepository();
    }

    @Bean
    public Gson injectGson() {
        return new Gson();
    }

    @Bean
    public PostService injectPostService(PostRepository repository, MessageProducer messageProducer) {
        logger.info("🚀 Initialized Post Service");
        return new PostService(repository, messageProducer);
    }

    @Bean
    public OrderService injectOrderService( MessageProducer messageProducer) {
        logger.info("🚀 Initialized Order Service");
        return new OrderService(messageProducer);
    }
}
