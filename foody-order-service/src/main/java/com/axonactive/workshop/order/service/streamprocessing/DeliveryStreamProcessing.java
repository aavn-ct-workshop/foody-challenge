package com.axonactive.workshop.order.service.streamprocessing;

import com.axonactive.workshop.order.service.event.model.DeliveryModel;
import com.axonactive.workshop.order.service.impl.OrderService;
import com.axonactive.workshop.order.service.model.DeliveryStatus;
import com.axonactive.workshop.order.service.model.OrderStatus;
import com.axonactive.workshop.order.service.model.PartialUpdateOrderRequestBody;
import com.axonactive.workshop.order.service.streamprocessing.serde.WrapperSerde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class DeliveryStreamProcessing extends AbstractStreamProcessingService {

    private static final Logger LOG = LoggerFactory.getLogger(DeliveryStreamProcessing.class);

    private OrderService orderService;

    @Autowired
    public DeliveryStreamProcessing(Environment env, OrderService orderService) {
        super(env, "delivery_stream_processing");
        this.orderService = orderService;

        if (env.getProperty("stream.processing.enable", Boolean.class) == true) {
            LOG.info("Start stream processing");
            startStreamProcessing();
        }
    }

    @Override
    protected void initStreamTopology() {
        StreamsBuilder builder = getBuilder();

        KStream<String, DeliveryModel> source = builder.stream(getEnv().getProperty("driver.service.kafka.topic"),
                Consumed.with(Serdes.String(), new WrapperSerde<DeliveryModel>(DeliveryModel.class)));

        source.filter(((key, value) -> Objects.nonNull(value)))
            .filter((key, value) -> !value.getStatus().equals(DeliveryStatus.WAITING_FOR_DRIVER))
            .foreach((key, value) -> processing(value));
    }

    private void processing(DeliveryModel deliveryModel) {
        Optional<String> orderId = Optional.ofNullable(deliveryModel).map(DeliveryModel::getOrderId);
        if (orderId.isEmpty()) {
            return;
        }
        try {
            updateOrderStatus(deliveryModel);
        } catch (Exception e) {
            LOG.warn("Cannot processing order with id [{}]. {}", orderId.get(), e.getMessage());
        }
    }

    private void updateOrderStatus(DeliveryModel deliveryModel) {
        PartialUpdateOrderRequestBody partialUpdateOrderRequestBody = new PartialUpdateOrderRequestBody();

        switch(deliveryModel.getStatus()) {
            case APPROVED:
                partialUpdateOrderRequestBody.setStatus(OrderStatus.GO_TO_SHOP);
                break;
            case REJECTED:
                partialUpdateOrderRequestBody.setStatus(OrderStatus.REJECTED_BY_DRIVER);
                break;
            case COMPLETED:
                partialUpdateOrderRequestBody.setStatus(OrderStatus.COMPLETED);
                break;
            default:
                return;
        }
        partialUpdateOrderRequestBody.setDriverId(deliveryModel.getDriverId());
        orderService.partialUpdateOrder(deliveryModel.getOrderId(), partialUpdateOrderRequestBody);
    }
}
