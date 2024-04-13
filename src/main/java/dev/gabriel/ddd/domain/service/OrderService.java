package dev.gabriel.ddd.domain.service;

import dev.gabriel.ddd.domain.entity.OrderItem;
import dev.gabriel.ddd.domain.entity.Customer;
import dev.gabriel.ddd.domain.entity.Order;

import java.util.Arrays;
import java.util.UUID;

public class OrderService {

    public static double total(Order[] orders) {
        return Arrays.stream(orders).mapToDouble(Order::getTotal).sum();
    }

    public static Order placeOrder(Customer customer, OrderItem[] items) {

        if (items.length == 0) {
            throw new IllegalArgumentException("order must have at lease one item");
        }

        var order = new Order(UUID.randomUUID(), customer.getId(), items);
        customer.addRewardPoints((int) order.getTotal() / 2);
        return order;
    }
}
