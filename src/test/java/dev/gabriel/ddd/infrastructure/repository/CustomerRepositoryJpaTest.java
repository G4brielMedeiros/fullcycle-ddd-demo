package dev.gabriel.ddd.infrastructure.repository;

import dev.gabriel.ddd.domain.entity.Customer;
import dev.gabriel.ddd.domain.value.Address;
import dev.gabriel.ddd.infrastructure.repository.customer.CustomerDAO;
import dev.gabriel.ddd.infrastructure.repository.customer.CustomerRepositoryJPA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest(
        properties = {
                "spring.datasource.url=jdbc:h2:mem:testdb",
                "spring.jpa.hibernate.ddl-auto=create-drop"
        },
        showSql = false)
class CustomerRepositoryJpaTest {

    @Autowired
    private CustomerDAO customerDAO;

    private CustomerRepositoryJPA customerRepositoryJPA;

    private Customer testCustomer;

    @BeforeEach
    void setUp() {
        customerRepositoryJPA = new CustomerRepositoryJPA(customerDAO);
        var testAddress = new Address("street", 10, "12345", "Vice City");
        testCustomer = new Customer(UUID.randomUUID(), "alice");
        testCustomer.setAddress(testAddress);
    }

    @Test
    void shouldCreateCustomer() {

        customerRepositoryJPA.create(testCustomer);

        var foundCustomer = customerRepositoryJPA.find(testCustomer.getId());

        assertEquals(testCustomer, foundCustomer);
    }

    @Test
    void shouldUpdateCustomer() {

        customerRepositoryJPA.create(testCustomer);

        var foundCustomer = customerRepositoryJPA.find(testCustomer.getId());

        assertEquals(testCustomer, foundCustomer);

        testCustomer.changeName("new name");

        customerRepositoryJPA.create(testCustomer);

        var updatedCustomer = customerRepositoryJPA.find(testCustomer.getId());

        assertEquals(testCustomer, updatedCustomer);
    }

    @Test
    void shouldFindCustomer() {

        customerRepositoryJPA.create(testCustomer);

        var CustomerModel = customerRepositoryJPA.find(testCustomer.getId());

        assertEquals(testCustomer, CustomerModel);

    }

    @Test
    void shouldFindAllCustomers() {

        var testAddress2 = new Address("street2", 102, "123452", "Vice City 2");
        var testCustomer2 = new Customer(UUID.randomUUID(), "bob");
        testCustomer2.setAddress(testAddress2);

        customerRepositoryJPA.create(testCustomer);
        customerRepositoryJPA.create(testCustomer2);

        Customer[] all = customerRepositoryJPA.findAll();

        assertTrue(Arrays.asList(all).contains(testCustomer));
        assertTrue(Arrays.asList(all).contains(testCustomer2));
        assertEquals(2, all.length);
    }
}