package dev.gabriel.ddd.infrastructure.repository.customer;

import dev.gabriel.ddd.domain.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@RequiredArgsConstructor
@Repository
public class CustomerRepositoryJPA implements CustomerRepository {

    private final CustomerDAO dao;

    @Override
    public void create(Customer entity) {
        dao.save(CustomerModel.from(entity));
    }

    @Override
    public void update(Customer entity) {
        dao.save(CustomerModel.from(entity));
    }

    @Override
    public Customer find(UUID id) {
        return dao.findById(id).orElseThrow().toEntity();
    }

    @Override
    public Customer[] findAll() {
        return dao.findAll().stream().map(CustomerModel::toEntity).toArray(Customer[]::new);
    }
}
