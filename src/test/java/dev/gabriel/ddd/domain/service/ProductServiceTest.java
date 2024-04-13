package dev.gabriel.ddd.domain.service;

import dev.gabriel.ddd.domain.entity.Product;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductServiceTest {

    @Test
    void shouldChangeThePriceOfAllProducts() {

        var product1 = new Product(UUID.randomUUID(), "product1", 10);
        var product2 = new Product(UUID.randomUUID(), "product2", 20);
        var products = new Product[]{product1, product2};

        ProductService.increasePrice(products, 1);

        assertEquals(20, product1.getPrice());
        assertEquals(40, product2.getPrice());
    }
}