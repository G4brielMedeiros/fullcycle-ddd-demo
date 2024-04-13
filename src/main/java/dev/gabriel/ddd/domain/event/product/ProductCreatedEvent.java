package dev.gabriel.ddd.domain.event.product;

import dev.gabriel.ddd.domain.entity.Product;
import dev.gabriel.ddd.domain.event.shared.Event;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ProductCreatedEvent implements Event {
    private final LocalDateTime createdAt = LocalDateTime.now();
    private final Product eventData;
}
