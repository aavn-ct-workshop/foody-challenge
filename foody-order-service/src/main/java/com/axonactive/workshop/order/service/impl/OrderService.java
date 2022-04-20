package com.axonactive.workshop.order.service.impl;

import java.util.List;

import com.axonactive.workshop.order.service.model.CreateOrderRequestBody;
import com.axonactive.workshop.order.service.model.OrderModel;
import com.axonactive.workshop.order.service.model.PartialUpdateOrderRequestBody;

public interface OrderService {

    public String createOrder(CreateOrderRequestBody requestBody);

    public void partialUpdateOrder(String orderId, PartialUpdateOrderRequestBody requestBody);

    public OrderModel getOrderById(String orderId);

    public List<OrderModel> getOrders();

}
