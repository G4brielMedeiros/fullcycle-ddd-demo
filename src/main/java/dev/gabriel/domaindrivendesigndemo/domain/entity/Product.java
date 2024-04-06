package dev.gabriel.domaindrivendesigndemo.domain.entity;

import lombok.Getter;

import java.util.UUID;

public class Product {


    private final UUID id;

    @Getter
    private String name;

    @Getter
    private double price;

    public Product(UUID id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        validate();
    }

    private void validate() {
        if (this.id == null) {
            throw new IllegalArgumentException("id is required");
        }
        if (this.name == null) {
            throw new IllegalArgumentException("name is required");
        }
        if (this.name.isBlank()) {
            throw new IllegalArgumentException("name is required");
        }
        if (this.price < 0) {
            throw new IllegalArgumentException("price must be greater than 0");
        }
    }

    public void changeName(String newName) {
        this.name = newName;
        validate();
    }

    public void changePrice(double newPrice) {
        this.price = newPrice;
        validate();
    }
}
