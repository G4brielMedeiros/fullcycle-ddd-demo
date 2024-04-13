package dev.gabriel.ddd.domain.event.product;

import dev.gabriel.ddd.domain.event.shared.EventHandler;

public class SendEmailWhenProductIsCreatedHandler implements EventHandler<ProductCreatedEvent> {

    @Override
    public void handle(ProductCreatedEvent event) {
        System.out.println("sending email...");
    }
}
