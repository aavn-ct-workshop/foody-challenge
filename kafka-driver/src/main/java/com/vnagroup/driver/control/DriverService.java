package com.vnagroup.driver.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.vnagroup.driver.entity.Delivery;
import com.vnagroup.driver.entity.Driver;
import com.vnagroup.driver.entity.Order;
import com.vnagroup.driver.entity.DeliveryStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DriverService {

    @Autowired
    Publisher publisher;

    List<Order> orders = new ArrayList<>();

    List<Delivery> deliveries = new ArrayList<>();

    List<Driver> drivers = new ArrayList<>();

    public List<Order> getAllReadyOrders() {
        return orders;
    }

    public void storeNewOrder(Order order) {
        this.orders.add(order);
    }

    public void updateStatus(Delivery delivery) {
        if (isDuplicatedOrder(delivery)) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Duplicated order"
            );
        }

        if (isDriverBusy(delivery)) {
            delivery.setStatus(DeliveryStatus.REJECTED);
            publisher.updateStatus(delivery);
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Driver busy"
            );
        }

        deliveries.stream()
            .filter(existingDelivery -> 
                existingDelivery.getDriverId().equals(delivery.getDriverId())
                && existingDelivery.getOrderId().equals(delivery.getOrderId())
            )
            .findAny()
            .ifPresentOrElse(existingDelivery -> existingDelivery.setStatus(delivery.getStatus()), () -> deliveries.add(delivery));
        publisher.updateStatus(delivery);
    }

    private boolean isDuplicatedOrder(Delivery delivery) {
        Optional<Delivery> optExistingDelivery = deliveries.stream()
            .filter(existingDelivery -> 
                existingDelivery.getOrderId().equals(delivery.getOrderId())
                && !existingDelivery.getDriverId().equals(delivery.getDriverId())
            )
            .findAny();
        return optExistingDelivery.isPresent();
    }

    private boolean isDriverBusy(Delivery delivery) {
        Optional<Delivery> optExistingDelivery = deliveries.stream()
            .filter(existingDelivery -> 
                !existingDelivery.getOrderId().equals(delivery.getOrderId())
                && existingDelivery.getDriverId().equals(delivery.getDriverId())
                && existingDelivery.getStatus() == DeliveryStatus.APPROVED
            )
            .findAny();
        return optExistingDelivery.isPresent();
    }
}
