package com.workshop.notifier.model;

public enum DeliveryStatus {
    WAITING_FOR_DRIVER("WAITING_FOR_DRIVER"),
    APPROVED("COMPLETED"),
    REJECTED("REJECTED");

    private String name;

    DeliveryStatus(String name) {
        this.name = name;
    }
}
