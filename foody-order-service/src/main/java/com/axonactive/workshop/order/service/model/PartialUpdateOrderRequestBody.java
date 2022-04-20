package com.axonactive.workshop.order.service.model;

public class PartialUpdateOrderRequestBody {

    private String driverId;

    private OrderStatus status;

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
