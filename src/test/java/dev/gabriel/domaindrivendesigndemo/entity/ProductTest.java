package dev.gabriel.domaindrivendesigndemo.entity;

import dev.gabriel.domaindrivendesigndemo.domain.entity.Product;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductTest {


    @Test
    public void shouldThrowError_whenIdIsNull() {
        var ex = assertThrows(IllegalArgumentException.class, () -> new Product(null, "Product Name", 99.99));

        assertEquals("id is required", ex.getMessage());
    }

    @Test
    public void shouldThrowError_whenNameIsNull() {
        var ex = assertThrows(IllegalArgumentException.class, () -> new Product(UUID.randomUUID(), null, 99.99));

        assertEquals("name is required", ex.getMessage());
    }

    @Test
    public void shouldThrowError_whenNameIsBlank() {
        var ex = assertThrows(IllegalArgumentException.class, () -> new Product(UUID.randomUUID(), " ", 99.99));

        assertEquals("name is required", ex.getMessage());
    }

    @Test
    public void shouldThrowError_whenPriceIsNegative() {
        var ex = assertThrows(IllegalArgumentException.class, () -> new Product(UUID.randomUUID(), "name", -54.99));

        assertEquals("price must be greater than 0", ex.getMessage());
    }

    @Test
    public void shouldChangeName() {
        var product = new Product(UUID.randomUUID(), "name", 49.99);
        var newName = "new name";

        product.changeName(newName);

        assertEquals(newName, product.getName());
    }

    @Test
    public void shouldChangePrice() {
        var product = new Product(UUID.randomUUID(), "name", 49.99);
        var newPrice = 149.99;

        product.changePrice(newPrice);

        assertEquals(newPrice, product.getPrice());
    }
}
