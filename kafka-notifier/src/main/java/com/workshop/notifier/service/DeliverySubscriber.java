package com.workshop.notifier.service;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.workshop.notifier.model.Delivery;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class DeliverySubscriber {

    Logger logger = LogManager.getLogger(DeliverySubscriber.class);

    private final NotifierService service;

    public DeliverySubscriber(NotifierService service) {
        this.service = service;
    }

    @KafkaListener(topics = "#{'${spring.kafka.subscriber.deliveries-topic}'.split(',')}", groupId = "#{'${spring.kafka.groupId}'}", containerFactory = "kafkaDeliveryListenerContainerFactory")
    public void listenNewDelivery(Delivery delivery) throws JsonProcessingException {
        logger.info("listening delivery: " + delivery);
        this.service.notifyNewDelivery(delivery);
    }
}
