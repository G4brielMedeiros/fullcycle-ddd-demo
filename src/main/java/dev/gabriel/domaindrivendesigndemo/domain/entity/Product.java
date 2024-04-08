package dev.gabriel.domaindrivendesigndemo.domain.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@Getter
@EqualsAndHashCode
public class Product {

    private final UUID id;

    private String name;

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
