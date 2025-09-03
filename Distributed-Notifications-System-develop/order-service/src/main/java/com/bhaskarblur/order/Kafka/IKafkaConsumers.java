package com.bhaskarblur.order.Kafka;

public interface IKafkaConsumers {

    void Notify(String topic, String message);
}