package com.axonactive.workshop.order.service.event.model;

import com.axonactive.workshop.order.service.model.DeliveryStatus;

public class DeliveryModel {

    private String driverId;

    private String orderId;

    private DeliveryStatus status;

    public String getOrderId() {
        return orderId;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }
}
