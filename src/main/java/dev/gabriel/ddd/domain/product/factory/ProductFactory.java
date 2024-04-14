package dev.gabriel.ddd.domain.product.factory;

import dev.gabriel.ddd.domain.product.entity.Product;

import java.util.UUID;

public class ProductFactory {
    public static Product create(String name, double price) {
        return new Product(UUID.randomUUID(), name, price);
    }
}
