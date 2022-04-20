package com.vnagroup.driver.entity;

public enum DeliveryStatus {
    WAITING_FOR_DRIVER("WAITING_FOR_DRIVER"),
    APPROVED("APPROVED"),
    REJECTED("REJECTED"),
    COMPLETED("COMPLETED");

    private String name;

    DeliveryStatus(String name) {
        this.name = name;
    }
}
