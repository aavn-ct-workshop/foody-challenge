package com.axonactive.workshop.order.service.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class CreateOrderRequestBody {

    private String hostId;

    private String hostName;

    private String driverId;

    private String deliveryAddress;

    @Valid
    private List<CreateOrderItemRequestBody> orderItems = new ArrayList<>();

    @NotNull
    @Length(max = 64)
    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    @NotNull
    @Length(max = 64)
    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    @NotEmpty
    public List<CreateOrderItemRequestBody> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<CreateOrderItemRequestBody> orderItems) {
        this.orderItems = orderItems;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}
