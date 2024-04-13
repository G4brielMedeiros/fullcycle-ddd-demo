package dev.gabriel.ddd.infrastructure.order.repository.jpa;

import dev.gabriel.ddd.domain.checkout.entity.Order;
import dev.gabriel.ddd.domain.checkout.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class OrderRepositoryJPA implements OrderRepository {

    private final OrderDAO orderDAO;

    @Override
    public void create(Order entity) {
        orderDAO.save(OrderModel.from(entity));
    }

    @Override
    public void update(Order entity) {
        orderDAO.save(OrderModel.from(entity));
    }

    @Override
    public Order find(UUID id) {
        return orderDAO.findById(id).orElseThrow().toEntity();
    }

    @Override
    public Order[] findAll() {
        return orderDAO.findAll().stream().map(OrderModel::toEntity).toArray(Order[]::new);
    }
}
