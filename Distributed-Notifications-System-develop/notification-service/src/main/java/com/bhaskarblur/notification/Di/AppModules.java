package com.bhaskarblur.notification.Di;

import com.bhaskarblur.notification.Kafka.MessageConsumer;
import com.bhaskarblur.notification.Repositories.NotificationRepository;
import com.bhaskarblur.notification.Services.NotificationService;
import com.bhaskarblur.notification.Utils.DateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class AppModules {
    private static final Logger logger = LoggerFactory.getLogger(AppModules.class);

    @Bean
    public Gson injectGson() {
        return new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateAdapter())
                .create();
    }

    @Bean
    public NotificationService injectNotificationService(NotificationRepository repository, MessageConsumer messageConsumer) {
        logger.info("🚀 Initialized Notification Service");
        return new NotificationService(repository, messageConsumer);
    }
}