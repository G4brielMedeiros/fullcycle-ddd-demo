package dev.gabriel.ddd.domain.customer.entity;

import dev.gabriel.ddd.domain.customer.value.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    private Customer customer;
    private final Address address = new Address("st", 1, "1234-5", "Winter Springs");

    @BeforeEach
    public void setup() {
        customer = new Customer(UUID.randomUUID(), "bob");
    }

    @Test
    public void shouldThrowError_whenIdIsEmpty() {
        var ex = assertThrows(IllegalArgumentException.class, () -> new Customer(null, "name"));

        assertEquals("id is required", ex.getMessage());
    }

    @Test
    public void shouldThrowError_whenNameIsBlank() {
        var ex = assertThrows(IllegalArgumentException.class, () -> new Customer(UUID.randomUUID(), null));
        var ex2 = assertThrows(IllegalArgumentException.class, () -> new Customer(UUID.randomUUID(), ""));
        var ex3 = assertThrows(IllegalArgumentException.class, () -> new Customer(UUID.randomUUID(), " "));

        assertEquals("name is required", ex.getMessage());
        assertEquals("name is required", ex2.getMessage());
        assertEquals("name is required", ex3.getMessage());
    }

    @Test
    public void shouldThrowError_whenNewNameIsBlank() {
        var customer = new Customer(UUID.randomUUID(), "bob");

        var ex = assertThrows(IllegalArgumentException.class, () -> customer.changeName(null));
        var ex2 = assertThrows(IllegalArgumentException.class, () -> customer.changeName(""));
        var ex3 = assertThrows(IllegalArgumentException.class, () -> customer.changeName(" "));

        assertEquals("name is required", ex.getMessage());
        assertEquals("name is required", ex2.getMessage());
        assertEquals("name is required", ex3.getMessage());
    }

    @Test
    public void shouldChangeName() {
        String newName = "alice";

        customer.changeName(newName);

        assertEquals(newName, customer.getName());
    }

    @Test
    public void shouldActivateCustomer() {
        customer.setAddress(address);
        customer.activate();
        assertTrue(customer.isActive());
    }

    @Test
    public void shouldDeactivateCustomer() {
        customer.setAddress(address);
        customer.deactivate();
        assertFalse(customer.isActive());
    }

    @Test
    public void shouldThrowError_whenActivatingWithoutAddress() {
        var ex = assertThrows(IllegalArgumentException.class, () -> customer.activate());
        assertEquals("address is mandatory to activate a customer", ex.getMessage());
    }

    @Test
    void shouldAddRewardPoints() {
        var customer = new Customer(UUID.randomUUID(), "name");
        assertEquals(0, customer.getRewardPoints());

        customer.addRewardPoints(10);
        assertEquals(10, customer.getRewardPoints());

        customer.addRewardPoints(10);
        assertEquals(20, customer.getRewardPoints());
    }
}