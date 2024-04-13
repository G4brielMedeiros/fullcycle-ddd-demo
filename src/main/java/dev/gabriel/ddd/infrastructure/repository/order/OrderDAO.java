package dev.gabriel.ddd.infrastructure.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderDAO extends JpaRepository<OrderModel, UUID> {
}
