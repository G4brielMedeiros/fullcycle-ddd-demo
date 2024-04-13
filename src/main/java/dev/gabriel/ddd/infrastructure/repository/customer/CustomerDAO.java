package dev.gabriel.ddd.infrastructure.repository.customer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerDAO extends JpaRepository<CustomerModel, UUID> {
}
