package dev.gabriel.ddd.infrastructure.repository.product;

import dev.gabriel.ddd.domain.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@RequiredArgsConstructor
@Repository
public class ProductRepositoryJPA implements ProductRepository {

    private final ProductDAO dao;

    @Override
    public void create(Product entity) {
        dao.save(ProductModel.from(entity));
    }

    @Override
    public void update(Product entity) {
        dao.save(ProductModel.from(entity));
    }

    @Override
    public Product find(UUID id) {
        return dao.findById(id).orElseThrow().toEntity();
    }

    @Override
    public Product[] findAll() {
        return dao.findAll().stream().map(ProductModel::toEntity).toArray(Product[]::new);
    }
}
