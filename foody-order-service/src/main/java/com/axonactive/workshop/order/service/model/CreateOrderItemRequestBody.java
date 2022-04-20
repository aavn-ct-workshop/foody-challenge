package com.axonactive.workshop.order.service.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CreateOrderItemRequestBody {

    private String name;

    private int price;

    private int quantity;

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @NotNull
    @Min(1)
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
