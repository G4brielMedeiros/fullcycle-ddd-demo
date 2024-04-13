package dev.gabriel.ddd.domain.customer.entity;

import dev.gabriel.ddd.domain.customer.value.Address;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@EqualsAndHashCode
public class Customer {

    private final UUID id;

    private String name;

    @Setter
    private Address address;

    private boolean active;

    private int rewardPoints = 0;

    public Customer(UUID id, String name) {
        this.id = id;
        this.name = name;
        this.active = true;
        validate();
    }

    public void changeName(String newName) {
        this.name = newName;
        validate();
    }

    public void activate() {
        if (this.address == null) {
            throw new IllegalArgumentException("address is mandatory to activate a customer");
        }

        this.active = true;
    }

    public void deactivate() {
        this.active = false;
    }

    public void addRewardPoints(int points) {
        this.rewardPoints += points;
    }

    public void validate() {
        if (this.id == null) {
            throw new IllegalArgumentException("id is required");
        }
        if (this.name == null) {
            throw new IllegalArgumentException("name is required");
        }
        if (this.name.isBlank()) {
            throw new IllegalArgumentException("name is required");
        }
    }

}
