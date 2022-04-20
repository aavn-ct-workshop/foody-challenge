package com.workshop.notifier.service;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.workshop.notifier.model.OrderModel;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderSubscriber {
    Logger logger = LogManager.getLogger(OrderSubscriber.class);

    private final NotifierService service;

    public OrderSubscriber(NotifierService service) {
        this.service = service;
    }

    @KafkaListener(topics = "#{'${spring.kafka.subscriber.orders-topic}'.split(',')}", groupId = "#{'${spring.kafka.groupId}'}", containerFactory = "kafkaOrderListenerContainerFactory")
    public void listenNewOrder(OrderModel order) throws JsonProcessingException {
        logger.info("Received order: " + order.toString());
        this.service.notifyNewOrder(order);
    }
}
