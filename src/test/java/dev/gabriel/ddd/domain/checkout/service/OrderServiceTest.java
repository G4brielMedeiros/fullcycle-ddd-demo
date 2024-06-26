package dev.gabriel.ddd.domain.checkout.service;

import dev.gabriel.ddd.domain.customer.entity.Customer;
import dev.gabriel.ddd.domain.checkout.entity.Order;
import dev.gabriel.ddd.domain.checkout.entity.OrderItem;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderServiceTest {

    @Test
    void shouldGetTotalOfAllOrders() {

        var item1 = new OrderItem(randomUUID(), randomUUID(), "item1", 100, 1);
        var item2 = new OrderItem(randomUUID(), randomUUID(), "item2", 200, 1);
        var item3 = new OrderItem(randomUUID(), randomUUID(), "item2", 300, 1);

        var order1 = new Order(randomUUID(), randomUUID(), List.of(item1).toArray(OrderItem[]::new));
        var order2 = new Order(randomUUID(), randomUUID(), List.of(item2).toArray(OrderItem[]::new));
        var order3 = new Order(randomUUID(), randomUUID(), List.of(item3).toArray(OrderItem[]::new));

        var total = OrderService.total(List.of(order1, order2, order3).toArray(Order[]::new));

        assertEquals(600, total );
    }

    @Test
    void shouldPlaceAnOrder() {
        var customer = new Customer(randomUUID(), "customer1");
        var item1 = new OrderItem(randomUUID(), randomUUID(), "item1", 10, 1);

        var order = OrderService.placeOrder(customer, List.of(item1).toArray(OrderItem[]::new));

        assertEquals(5, customer.getRewardPoints());
        assertEquals(10, order.getTotal());
    }
}