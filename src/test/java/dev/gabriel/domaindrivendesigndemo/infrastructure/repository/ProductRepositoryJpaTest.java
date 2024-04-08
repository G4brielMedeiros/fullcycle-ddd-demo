package dev.gabriel.domaindrivendesigndemo.infrastructure.repository;

import dev.gabriel.domaindrivendesigndemo.domain.entity.Product;
import dev.gabriel.domaindrivendesigndemo.infrastructure.repository.product.ProductDAO;
import dev.gabriel.domaindrivendesigndemo.infrastructure.repository.product.ProductRepositoryJPA;
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
        showSql = false
)
class ProductRepositoryJpaTest {

    @Autowired
    private ProductDAO productDAO;

    private ProductRepositoryJPA productRepositoryJpa;

    private Product testProduct;

    @BeforeEach
    void setUp() {
        productRepositoryJpa = new ProductRepositoryJPA(productDAO);
        testProduct = new Product(UUID.randomUUID(), "name", 100);
    }

    @Test
    void shouldCreateProduct() {

        productRepositoryJpa.create(testProduct);

        var foundProduct = productRepositoryJpa.find(testProduct.getId());

        assertEquals(testProduct, foundProduct);
    }

    @Test
    void shouldUpdateProduct() {

        productRepositoryJpa.create(testProduct);

        var foundProduct = productRepositoryJpa.find(testProduct.getId());

        assertEquals(testProduct, foundProduct);

        testProduct.changeName("new name");
        testProduct.changePrice(200);

        productRepositoryJpa.update(testProduct);

        var updatedProduct = productRepositoryJpa.find(testProduct.getId());

        assertEquals(testProduct, updatedProduct);
    }

    @Test
    void shouldFindProduct() {

        productRepositoryJpa.create(testProduct);

        var foundProduct = productRepositoryJpa.find(testProduct.getId());

        assertEquals(testProduct, foundProduct);

    }

    @Test
    void shouldFindAllProducts() {

        var testProduct2 = new Product(UUID.randomUUID(), "name2", 200);
        productRepositoryJpa.create(testProduct);
        productRepositoryJpa.create(testProduct2);
        Product[] all = productRepositoryJpa.findAll();

        assertTrue(Arrays.stream(all).anyMatch(productModel -> testProduct.getId() == productModel.getId()));
        assertTrue(Arrays.stream(all).anyMatch(productModel -> testProduct2.getId() == productModel.getId()));
        assertEquals(2, all.length);
    }
}

//could not execute statement [Referential integrity constraint violation: "FK64S72LNYPEX5IATA9YIORNIWJ: PUBLIC.T_ORDERS FOREIGN KEY(CUSTOMER_ID) REFERENCES PUBLIC.T_CUSTOMER(ID) (UUID 'ab6c699f-ca66-476b-a680-f795bbc3f9e7')"; SQL statement:
//insert into t_orders (customer_id,total,id) values (?,?,?) [23506-224]] [insert into t_orders (customer_id,total,id) values (?,?,?)]; SQL [insert into t_orders (customer_id,total,id) values (?,?,?)]; constraint [FK64S72LNYPEX5IATA9YIORNIWJ]