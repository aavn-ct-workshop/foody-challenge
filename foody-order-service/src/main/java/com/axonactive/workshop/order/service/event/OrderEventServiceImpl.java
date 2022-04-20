package com.axonactive.workshop.order.service.event;

import com.axonactive.workshop.order.service.model.OrderModel;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class OrderEventServiceImpl extends AbstractEventService<OrderModel> {

    private static final Logger LOG = LoggerFactory.getLogger(OrderEventServiceImpl.class);

    @Autowired
    public OrderEventServiceImpl(Environment env) {
        super(env);
    }

    protected void sendEvent(OrderModel order) {
        String topicName = env.getProperty("order.service.kafka.topic");
        String key = order.getId();

        LOG.info("Sending order {} to topic {}", order, topicName);
        ProducerRecord<String, OrderModel> record = new ProducerRecord<>(
            topicName,
            key,
                order
        );

        getProducer().send(record, (RecordMetadata r, Exception e) -> {
            if (e != null) {
                LOG.error("Error producing to topic [{}]. Because of: {}", topicName, e.getMessage());
                LOG.debug("", e);
            }
        });
    }
}
