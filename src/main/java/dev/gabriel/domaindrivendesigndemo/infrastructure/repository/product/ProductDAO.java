package dev.gabriel.domaindrivendesigndemo.infrastructure.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductDAO extends JpaRepository<ProductModel, UUID> {
}
