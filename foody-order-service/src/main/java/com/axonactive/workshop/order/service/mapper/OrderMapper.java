package com.axonactive.workshop.order.service.mapper;

import com.axonactive.workshop.order.service.entity.Order;
import com.axonactive.workshop.order.service.entity.OrderItem;
import com.axonactive.workshop.order.service.event.model.DeliveryModel;
import com.axonactive.workshop.order.service.model.CreateOrderItemRequestBody;
import com.axonactive.workshop.order.service.model.CreateOrderRequestBody;
import com.axonactive.workshop.order.service.model.OrderModel;
import com.axonactive.workshop.order.service.model.PartialUpdateOrderRequestBody;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderMapper {

    public static final OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Order toOrderEntity(CreateOrderRequestBody createOrderRequestBody);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "status", source = "status", ignore = true)
    Order partialUpdateOrderEntity(@MappingTarget Order order, PartialUpdateOrderRequestBody partialUpdateOrderRequestBody);
    
    List<OrderItem> toOrderItems(List<CreateOrderItemRequestBody> createOrderItemRequestBody);

    OrderModel toOrderModel(Order order);

    @Mapping(target = "orderId", source = "id")
    @Mapping(target = "status", source = "status", ignore = true)
    DeliveryModel toDeliveryModel(OrderModel orderModel);

}
