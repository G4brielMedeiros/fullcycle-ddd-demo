package dev.gabriel.domaindrivendesigndemo.entity;

import lombok.Getter;

import java.util.UUID;

@Getter
public class OrderItem {

    private UUID id;
    private UUID productId;
    private String name;
    private double price;
    private int quantity;

    public OrderItem(UUID id, UUID productId, String name, double price, int quantity) {
        this.id = id;
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        validate();
    }

    private void validate() {
        if (this.quantity <= 0) {
            throw new IllegalArgumentException("quantity must be greater than 0");
        }
    }

    public double getOrderItemTotal() {
        return price * quantity;
    }

}
