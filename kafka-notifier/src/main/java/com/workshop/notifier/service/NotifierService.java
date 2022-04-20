package com.workshop.notifier.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.workshop.notifier.model.Delivery;
import com.workshop.notifier.model.OrderModel;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotifierService {

    SimpMessagingTemplate messageTemplate;

    ObjectMapper mapper;

    public NotifierService(ObjectMapper mapper, SimpMessagingTemplate messageTemplate) {
        this.mapper = mapper;
        this.messageTemplate = messageTemplate;
    }

    public void notifyNewDelivery(Delivery delivery) throws JsonProcessingException {
        this.messageTemplate.convertAndSend("/delivery", mapper.writeValueAsString(delivery));
    }

    public void notifyNewOrder(OrderModel order) throws JsonProcessingException {
        this.messageTemplate.convertAndSend("/order", mapper.writeValueAsString(order));
    }
    
}
