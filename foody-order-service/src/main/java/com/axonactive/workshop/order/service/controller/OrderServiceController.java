package com.axonactive.workshop.order.service.controller;

import com.axonactive.workshop.order.service.impl.OrderService;
import com.axonactive.workshop.order.service.model.CreateOrderRequestBody;
import com.axonactive.workshop.order.service.model.OrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/orders")
public class OrderServiceController {

    private OrderService orderService;

    @Autowired
    public OrderServiceController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderModel>> searchOrder() {
        List<OrderModel> orders = orderService.getOrders();
        return new ResponseEntity<>(orders, HttpStatus.CREATED);
    }
    
    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody @Valid @NotNull CreateOrderRequestBody requestBody) {
        String createdOrderId = orderService.createOrder(requestBody);
        return new ResponseEntity<>(createdOrderId, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderModel> getOrderById(@PathVariable("id") String id) {
        OrderModel order = orderService.getOrderById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

}
