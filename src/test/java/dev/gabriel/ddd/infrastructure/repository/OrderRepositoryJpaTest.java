package dev.gabriel.ddd.infrastructure.repository;

import dev.gabriel.ddd.domain.entity.Customer;
import dev.gabriel.ddd.domain.entity.Order;
import dev.gabriel.ddd.domain.entity.OrderItem;
import dev.gabriel.ddd.domain.entity.Product;
import dev.gabriel.ddd.domain.value.Address;
import dev.gabriel.ddd.infrastructure.repository.customer.CustomerDAO;
import dev.gabriel.ddd.infrastructure.repository.customer.CustomerRepositoryJPA;
import dev.gabriel.ddd.infrastructure.repository.order.OrderDAO;
import dev.gabriel.ddd.infrastructure.repository.order.OrderRepositoryJPA;
import dev.gabriel.ddd.infrastructure.repository.product.ProductDAO;
import dev.gabriel.ddd.infrastructure.repository.product.ProductRepositoryJPA;
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
class OrderRepositoryJpaTest {

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private CustomerDAO customerDAO;

    private OrderRepositoryJPA orderRepositoryJPA;
    private CustomerRepositoryJPA customerRepositoryJPA;

    private final UUID customerId = UUID.randomUUID();
    private final UUID productId = UUID.randomUUID();

    private Order testOrder;

    @BeforeEach
    void setUp() {
        orderRepositoryJPA = new OrderRepositoryJPA(orderDAO);
        customerRepositoryJPA = new CustomerRepositoryJPA(customerDAO);
        ProductRepositoryJPA productRepositoryJPA = new ProductRepositoryJPA(productDAO);

        productRepositoryJPA.create(new Product(productId, "product", 100));
        customerRepositoryJPA.create(new Customer(customerId, "bob"));

        var orderItems = new OrderItem[]{
                new OrderItem(UUID.randomUUID(), productId, "product", 100, 1),
                new OrderItem(UUID.randomUUID(), productId, "product", 100, 1)
        };
        testOrder = new Order(UUID.randomUUID(), customerId, orderItems);
    }

    @Test
    void shouldCreateOrder() {

        orderRepositoryJPA.create(testOrder);

        var foundOrder = orderRepositoryJPA.find(testOrder.getId());

        assertEquals(testOrder, foundOrder);
    }

    @Test
    void shouldFindOrder() {

        orderRepositoryJPA.create(testOrder);

        var foundOrder = orderRepositoryJPA.find(testOrder.getId());

        assertEquals(testOrder, foundOrder);

    }

    @Test
    void shouldFindAllOrders() {
        var orderItems2 = new OrderItem[]{
                new OrderItem(UUID.randomUUID(), productId, "product", 100, 1),
                new OrderItem(UUID.randomUUID(), productId, "product", 100, 1)
        };
        var testOrder2 = new Order(UUID.randomUUID(), customerId, orderItems2);

        orderRepositoryJPA.create(testOrder);
        orderRepositoryJPA.create(testOrder2);

        orderDAO.flush();

        Order[] all = orderRepositoryJPA.findAll();

        assertTrue(Arrays.asList(all).contains(testOrder));
        assertTrue(Arrays.asList(all).contains(testOrder2));
        assertEquals(2, all.length);
    }

    @Test
    void shouldUpdateOrder() {

        var alice = new Customer(UUID.randomUUID(), "alice");
        var address = new Address("street", 123, "12345", "city");
        alice.setAddress(address);
        customerRepositoryJPA.create(alice);

        var newOrderItems = new OrderItem[]{
                new OrderItem(UUID.randomUUID(), productId, "abc", 100, 1),
                new OrderItem(UUID.randomUUID(), productId, "xyz", 100, 1),
                testOrder.getOrderItems()[0]
        };


        var editedOrder = new Order(testOrder.getId(), alice.getId(), newOrderItems);

        orderRepositoryJPA.update(editedOrder);

        var foundOrder = orderRepositoryJPA.find(testOrder.getId());

        assertEquals(editedOrder, foundOrder);

    }
}