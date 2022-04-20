package com.vnagroup.driver.control;

import com.vnagroup.driver.entity.Order;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Subscriber {

    private final DriverService service;

    public Subscriber(DriverService service) {
        this.service = service;
    }

    @KafkaListener(topics = "#{'${spring.kafka.subscriber.topic}'.split(',')}", groupId = "#{'${spring.kafka.groupId}'}")
    public void listenNewOrder(Order order) {
        this.service.storeNewOrder(order);
    }
}
