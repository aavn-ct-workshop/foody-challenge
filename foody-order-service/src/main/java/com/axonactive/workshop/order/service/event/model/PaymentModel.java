package com.axonactive.workshop.order.service.event.model;

public class PaymentModel {
    String uuid;

    String userId;

    long orderTime;

    long proceedTime;

    boolean hasPaid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(long orderTime) {
        this.orderTime = orderTime;
    }

    public long getProceedTime() {
        return proceedTime;
    }

    public void setProceedTime(long proceedTime) {
        this.proceedTime = proceedTime;
    }

    public boolean isHasPaid() {
        return hasPaid;
    }

    public void setHasPaid(boolean hasPaid) {
        this.hasPaid = hasPaid;
    }
}
