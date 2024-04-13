package dev.gabriel.ddd.infrastructure.order.repository.jpa;

import dev.gabriel.ddd.domain.checkout.entity.Order;
import dev.gabriel.ddd.domain.checkout.entity.OrderItem;
import dev.gabriel.ddd.infrastructure.customer.repository.jpa.CustomerModel;
import dev.gabriel.ddd.infrastructure._shared.EntityModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "t_orders")
public class OrderModel implements EntityModel<Order> {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(updatable = false, insertable = false)
    private CustomerModel customer;

    @Column(name = "customer_id")
    private UUID customerId;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OrderItemModel> orderItems;

    private double total;

    public OrderModel(UUID id, UUID customerId, List<OrderItemModel> orderItems, double total) {
        this.id = id;
        this.customerId = customerId;
        this.orderItems = orderItems;
        this.total = total;
    }

    public static OrderModel from(Order order) {
        var items = Arrays.stream(order.getOrderItems())
                .map(item -> OrderItemModel.from(item, order.getId()))
                .collect(Collectors.toList());

        return new OrderModel(
                order.getId(),
                order.getCustomerId(),
                items,
                order.getTotal()
        );
    }

    @Override
    public Order toEntity() {
        return new Order(this.id, this.getCustomerId(), this.orderItems.stream().map(OrderItemModel::toEntity).toArray(OrderItem[]::new));
    }
}
