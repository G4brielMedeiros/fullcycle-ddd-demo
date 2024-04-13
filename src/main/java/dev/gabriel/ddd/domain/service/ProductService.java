package dev.gabriel.ddd.domain.service;

import dev.gabriel.ddd.domain.entity.Product;

import java.util.Arrays;

public class ProductService {


    public static Product[] increasePrice(Product[] products, double percentage) {


        Arrays.stream(products).forEach(product ->
                product.changePrice(product.getPrice() * percentage + product.getPrice())
        );
        return products;
    }
}
