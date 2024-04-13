package dev.gabriel.ddd.domain.product.event.handler;

import dev.gabriel.ddd.domain._shared.event.EventHandler;
import dev.gabriel.ddd.domain.product.event.ProductCreatedEvent;

public class SendEmailWhenProductIsCreatedHandler implements EventHandler<ProductCreatedEvent> {

    @Override
    public void handle(ProductCreatedEvent event) {
        System.out.println("sending email...");
    }
}
