package com.axonactive.workshop.order.service.impl;

import com.axonactive.workshop.order.service.entity.Order;
import com.axonactive.workshop.order.service.event.EventService;
import com.axonactive.workshop.order.service.mapper.OrderMapper;
import com.axonactive.workshop.order.service.model.*;
import com.axonactive.workshop.order.service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    private EventService<OrderModel> eventService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, EventService<OrderModel> eventService) {
        this.orderRepository = orderRepository;
        this.eventService = eventService;
    }

    @Override
    public String createOrder(CreateOrderRequestBody requestBody) {
        Order order = OrderMapper.INSTANCE.toOrderEntity(requestBody);
        order.setStatus(OrderStatus.VALIDATING);
        order = orderRepository.save(order);

        OrderModel orderModel = OrderMapper.INSTANCE.toOrderModel(order);
        eventService.writeEvent(orderModel);

        return order.getId();
    }

    @Override
    public void partialUpdateOrder(String orderId, PartialUpdateOrderRequestBody requestBody) {
        Order order = getOrder(orderId);
        OrderMapper.INSTANCE.partialUpdateOrderEntity(order, requestBody);

        order = updateOrderStatus(order, requestBody.getStatus());
        order = orderRepository.save(order);

        OrderModel orderModel = OrderMapper.INSTANCE.toOrderModel(order);
        eventService.writeEvent(orderModel);
    }

    @Override
    public List<OrderModel> getOrders() {
        return orderRepository.findAll().stream()
                .map(OrderMapper.INSTANCE::toOrderModel)
                .collect(Collectors.toList());
    }

    private Order updateOrderStatus(Order currentOrder, OrderStatus statusToUpdate) {
        validateOrderStatus(currentOrder, statusToUpdate);
        currentOrder.setStatus(statusToUpdate);
        return currentOrder;
    }

    @Override
    @Transactional
    public OrderModel getOrderById(String orderId) {
        Order order = getOrder(orderId);
        return OrderMapper.INSTANCE.toOrderModel(order);
    }

    private Order getOrder(String orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Order with id [%s] not found.", orderId)
            );
        }
        return order.get();
    }

    private void validateOrderStatus(Order currentOrder, OrderStatus statusToUpdate) {
        OrderStatus currentStatus = currentOrder.getStatus();
        int levelChanged = statusToUpdate.getOrder() - currentStatus.getOrder();
        if (levelChanged < 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("Order with id [%s] cannot change status to [%s].",
                            currentOrder.getId(),
                            OrderStatus.WAITING_FOR_DRIVER.getName()
                    )
            );
        }
    }
}
