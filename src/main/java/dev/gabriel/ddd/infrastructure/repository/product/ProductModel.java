package dev.gabriel.ddd.infrastructure.repository.product;

import dev.gabriel.ddd.domain.entity.Product;
import dev.gabriel.ddd.infrastructure.repository.EntityModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_product")
public class ProductModel implements EntityModel<Product> {

    @Id
    private UUID id;

    @NotBlank
    private String name;

    private double price;

    public static ProductModel from(Product product) {
        return new ProductModel(product.getId(), product.getName(), product.getPrice());
    }

    public Product toEntity() {
        return new Product(this.id, this.name, this.price);
    }

}


