package com.vnagroup.driver.entity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Delivery {
    
    private String orderId;

    private String driverId;

    private DeliveryStatus status;

    @NotEmpty
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @NotEmpty
    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    @NotNull
    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }
}
