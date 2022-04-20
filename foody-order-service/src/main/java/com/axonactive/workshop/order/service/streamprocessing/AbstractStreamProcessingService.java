package com.axonactive.workshop.order.service.streamprocessing;

import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

import java.util.Properties;

public abstract class AbstractStreamProcessingService implements StreamProcessingService {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractStreamProcessingService.class);

    private StreamsBuilder builder;

    private KafkaStreams streams;

    private Environment env;

    private Properties properties = new Properties();

    public AbstractStreamProcessingService(Environment env, String applicationId) {
        if (StringUtils.isBlank(applicationId)) {
            throw new IllegalArgumentException("Application ID cannot be blank");
        }
        this.env = env;
        builder = new StreamsBuilder();

        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, applicationId);
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, env.getProperty("kafka.bootstrap.servers"));
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        initStreamTopology();
        streams = new KafkaStreams(builder.build(), properties);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            LOG.info("Shutting down {}...", this.getClass().getName());
            LOG.info("Closing kafka producer...");
            stopStreamProcessing();
            LOG.info("DONE");
        }));
    }

    @Override
    public void startStreamProcessing() {
        streams.start();
    }

    @Override
    public void stopStreamProcessing() {
        streams.close();
    }

    protected abstract void initStreamTopology();

    public StreamsBuilder getBuilder() {
        return builder;
    }

    public KafkaStreams getStreams() {
        return streams;
    }

    public Environment getEnv() {
        return env;
    }
}
