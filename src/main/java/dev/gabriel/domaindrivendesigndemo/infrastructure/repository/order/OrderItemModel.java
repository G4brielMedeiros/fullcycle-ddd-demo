package dev.gabriel.domaindrivendesigndemo.infrastructure.repository.order;

import dev.gabriel.domaindrivendesigndemo.domain.entity.OrderItem;
import dev.gabriel.domaindrivendesigndemo.infrastructure.repository.EntityModel;
import dev.gabriel.domaindrivendesigndemo.infrastructure.repository.product.ProductModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "t_order_item")
public final class OrderItemModel implements EntityModel<OrderItem> {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(updatable = false, insertable = false)
    private ProductModel product;

    @Column(name = "product_id")
    private UUID productId;

    @ManyToOne
    @JoinColumn(updatable = false, insertable = false)
    private OrderModel order;

    @Column(name = "order_id")
    private UUID orderId;

    private int quantity;

    @NotBlank
    private String name;

    private double price;

    public OrderItemModel(UUID id, UUID productId, UUID orderId, int quantity, String name, double price) {
        this.id = id;
        this.productId = productId;
        this.orderId = orderId;
        this.quantity = quantity;
        this.name = name;
        this.price = price;
    }

    @Override
    public OrderItem toEntity() {
        return new OrderItem(this.getId(), this.getProductId(), this.getName(), this.getPrice(), this.getQuantity());
    }

    public static OrderItemModel from(OrderItem orderItem, UUID orderId) {
        return new OrderItemModel(
                orderItem.id(),
                orderItem.productId(),
                orderId,
                orderItem.quantity(),
                orderItem.name(),
                orderItem.price()
        );
    }
}
