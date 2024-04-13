package dev.gabriel.ddd.infrastructure.order.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderDAO extends JpaRepository<OrderModel, UUID> {
}
