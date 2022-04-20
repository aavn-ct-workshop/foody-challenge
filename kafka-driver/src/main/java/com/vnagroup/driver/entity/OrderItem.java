package com.vnagroup.driver.entity;

import java.util.Objects;

public class OrderItem {

    private String id;

    private String name;

    private int price;

    private int quantity;

    private Order order;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        OrderItem orderItem = (OrderItem) o;

        if (price != orderItem.price)
            return false;
        if (quantity != orderItem.quantity)
            return false;
        if (!Objects.equals(id, orderItem.id))
            return false;
        if (!Objects.equals(name, orderItem.name))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, quantity);
    }
}
