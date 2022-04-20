package com.vnagroup.driver.control;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.vnagroup.driver.entity.Delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Publisher {
    Logger logger = LogManager.getLogger(Publisher.class);

    @Value(value = "${spring.kafka.publisher.topic}")
    private String topic;
    
    @Autowired
    private KafkaTemplate<String, Delivery> kafkaTemplate;

    public void updateStatus(Delivery delivery) {
        logger.info("received delivery: " + delivery);
        kafkaTemplate.send(topic, delivery);
    }
}
