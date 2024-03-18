package dev.gabriel.domaindrivendesigndemo.entity;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderTest {


    @Test
    public void shouldThrowError_whenIdIsEmpty() {
        OrderItem[] orderItems = {};
        var ex = assertThrows(
                IllegalArgumentException.class,
                () -> new Order(null, UUID.randomUUID(), orderItems)
        );

        assertEquals("id is required", ex.getMessage());
    }

    @Test
    public void shouldThrowError_whenCustomerIdIsEmpty() {
        OrderItem[] orderItems = {};
        var ex = assertThrows(
                IllegalArgumentException.class,
                () -> new Order(UUID.randomUUID(), null, orderItems)
        );

        assertEquals("customerId is required", ex.getMessage());
    }

    @Test
    public void shouldThrowError_whenOrderItemArrayIsEmpty() {
        OrderItem[] orderItems = {};
        var ex = assertThrows(
                IllegalArgumentException.class,
                () -> new Order(UUID.randomUUID(), UUID.randomUUID(), orderItems)
        );

        assertEquals("orderItems must not be empty", ex.getMessage());
    }

    @Test
    public void shouldCalculateTotal() {
        OrderItem[] orderItems = {
                new OrderItem(UUID.randomUUID(), "item1", 10),
                new OrderItem(UUID.randomUUID(), "item2", 15)
        };

        var order = new Order(UUID.randomUUID(), UUID.randomUUID(), orderItems);

        assertEquals(25, order.getTotal());

    }
}