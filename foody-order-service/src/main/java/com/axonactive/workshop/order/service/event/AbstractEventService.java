package com.axonactive.workshop.order.service.event;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

import com.axonactive.workshop.order.service.event.model.DeliveryModel;
import com.axonactive.workshop.order.service.model.OrderModel;

public abstract class AbstractEventService<T> implements EventService<T> {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractEventService.class);

    protected KafkaProducer<String, OrderModel> orderProducer;

    protected KafkaProducer<String, DeliveryModel> deliveryProducer;

    protected Environment env;

    public AbstractEventService(Environment env) {
        this.env = env;
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, env.getProperty("kafka.bootstrap.servers"));
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        // create safe Producer
        configProps.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
        configProps.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");
        configProps.put(ProducerConfig.ACKS_CONFIG, "all");
        configProps.put(ProducerConfig.RETRIES_CONFIG, Integer.valueOf(Integer.MAX_VALUE));
        configProps.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, "5");

        orderProducer = new KafkaProducer<>(configProps);

        deliveryProducer = new KafkaProducer<>(configProps);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            LOG.info("Shutting down {}...", this.getClass().getName());
            LOG.info("Closing kafka producer...");
            orderProducer.close();
            LOG.info("DONE");
        }));
    }

    @Override
    public void writeEvent(T model) {
        if (model == null) {
            throw new IllegalArgumentException();
        }
        sendEvent(model);
    }

    protected abstract void sendEvent(T model);

    protected KafkaProducer<String, OrderModel> getProducer() {
        return orderProducer;
    }

    protected KafkaProducer<String, DeliveryModel> getDeliveryProducer() {
        return deliveryProducer;
    }
}
