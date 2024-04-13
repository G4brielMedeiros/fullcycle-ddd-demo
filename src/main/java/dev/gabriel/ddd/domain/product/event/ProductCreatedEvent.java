package dev.gabriel.ddd.domain.product.event;

import dev.gabriel.ddd.domain.product.entity.Product;
import dev.gabriel.ddd.domain._shared.event.Event;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ProductCreatedEvent implements Event {
    private final LocalDateTime createdAt = LocalDateTime.now();
    private final Product eventData;
}
