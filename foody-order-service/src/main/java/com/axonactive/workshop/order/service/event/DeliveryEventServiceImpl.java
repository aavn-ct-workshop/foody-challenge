package com.axonactive.workshop.order.service.event;

import com.axonactive.workshop.order.service.event.model.DeliveryModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class DeliveryEventServiceImpl extends AbstractEventService<DeliveryModel> {

    private static final Logger LOG = LoggerFactory.getLogger(DeliveryEventServiceImpl.class);

    @Autowired
    public DeliveryEventServiceImpl(Environment env) {
        super(env);
    }

    protected void sendEvent(DeliveryModel deliveryModel) {
        String key = deliveryModel.getOrderId();
        String topicName = env.getProperty("driver.service.kafka.topic");

        ProducerRecord<String, DeliveryModel> record = new ProducerRecord<>(
            topicName,
            key,
            deliveryModel
        );

        getDeliveryProducer().send(record, (RecordMetadata r, Exception e) -> {
            if (e != null) {
                LOG.error("Error producing to topic [{}]. Because of: {}", topicName, e.getMessage());
                LOG.debug("", e);
            }
        });
    }
}
