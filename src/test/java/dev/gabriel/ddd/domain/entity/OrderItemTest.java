package dev.gabriel.ddd.domain.entity;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderItemTest {


    @Test
    public void shouldThrowError_whenQuantityIsLessThanOrEqualToZero() {
        var ex = assertThrows(
                IllegalArgumentException.class,
                () -> new OrderItem(UUID.randomUUID(), UUID.randomUUID(), "item1", 10, -2)
        );

        assertEquals("quantity must be greater than 0", ex.getMessage());
    }

}