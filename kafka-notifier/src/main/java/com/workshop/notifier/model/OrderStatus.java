package com.workshop.notifier.model;

public enum OrderStatus {
    VALIDATING("VALIDATING", 0),
    WAITING_FOR_DRIVER("WAITING_FOR_DRIVER", 1),
    GO_TO_SHOP("GO_TO_SHOP", 2),
    DELIVERING("DELIVERING", 3),
    COMPLETED("COMPLETED", 4),
    REJECTED_BY_DRIVER("REJECTED_BY_DRIVER", 5);

    private String name;

    private int order;

    OrderStatus(String name, int order) {
        this.name = name;
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public int getOrder() {
        return order;
    }
}
