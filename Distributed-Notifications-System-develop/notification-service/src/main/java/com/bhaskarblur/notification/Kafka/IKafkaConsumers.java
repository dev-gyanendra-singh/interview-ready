package com.bhaskarblur.notification.Kafka;

public interface IKafkaConsumers {

    void Notify(String topic, String message);
}