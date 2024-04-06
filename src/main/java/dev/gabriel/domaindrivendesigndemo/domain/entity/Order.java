package dev.gabriel.domaindrivendesigndemo.domain.entity;

import lombok.Getter;

import java.util.Arrays;
import java.util.UUID;

@Getter
public class Order {

    private final UUID id;
    private final UUID customerId;
    private final OrderItem[] orderItems;
    private final double total;

    public Order(UUID id, UUID customerId, OrderItem[] orderItems) {
        this.id = id;
        this.customerId = customerId;
        this.orderItems = orderItems;
        this.total = total();
        validate();
    }

    private double total() {
        return Arrays.stream(orderItems).mapToDouble(OrderItem::getOrderItemTotal).sum();
    }

    public void validate() {
        if (id == null) {
            throw new IllegalArgumentException("id is required");
        }
        if (customerId == null) {
            throw new IllegalArgumentException("customerId is required");
        }
        if (orderItems == null || orderItems.length == 0) {
            throw new IllegalArgumentException("orderItems must not be empty");
        }
        if (Arrays.stream(this.orderItems).anyMatch(orderItem -> orderItem.quantity() <= 0)) {
            throw new IllegalArgumentException("quantity must be greater than 0");
        }
    }
}
