package dev.gabriel.ddd.infrastructure.customer.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerDAO extends JpaRepository<CustomerModel, UUID> {
}
