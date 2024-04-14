package dev.gabriel.ddd.domain.product.factory;

import dev.gabriel.ddd.domain.product.entity.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductFactoryTest {

    @Test
    void shouldCreateProduct() {
        var product = ProductFactory.create("product", 100);
        assertNotNull(product.getId());
        assertEquals("product", product.getName());
        assertEquals(100, product.getPrice());
    }
}