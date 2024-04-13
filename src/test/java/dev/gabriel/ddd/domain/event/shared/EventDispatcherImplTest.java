package dev.gabriel.ddd.domain.event.shared;

import dev.gabriel.ddd.domain.entity.Product;
import dev.gabriel.ddd.domain.event.product.ProductCreatedEvent;
import dev.gabriel.ddd.domain.event.product.SendEmailWhenProductIsCreatedHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class EventDispatcherImplTest {

    EventDispatcherImpl eventDispatcher;

    EventHandler<? extends Event> sendEmailWhenProductIsCreatedHandler;

    @BeforeEach
    void setUp() {
        eventDispatcher = new EventDispatcherImpl();
        sendEmailWhenProductIsCreatedHandler = Mockito.spy(new SendEmailWhenProductIsCreatedHandler());
    }

    @Test
    void shouldRegisterEventHandler() {

        eventDispatcher.register("ProductCreatedEvent", sendEmailWhenProductIsCreatedHandler);

        assertEquals(1, eventDispatcher.getEventHandlers().size());
        assertNotNull(eventDispatcher.getEventHandlers().get("ProductCreatedEvent"));
        assertEquals(SendEmailWhenProductIsCreatedHandler.class, eventDispatcher.getEventHandlers().get("ProductCreatedEvent").getFirst().getClass());
    }

    @Test
    void shouldUnregisterEventHandler() {
        eventDispatcher.register("ProductCreatedEvent", sendEmailWhenProductIsCreatedHandler);

        eventDispatcher.unregister("ProductCreatedEvent", sendEmailWhenProductIsCreatedHandler);

        assertEquals(0, eventDispatcher.getEventHandlers().get("ProductCreatedEvent").size());
    }

    @Test
    void shouldUnregisterAllEventHandlers() {
        eventDispatcher.register("ProductCreatedEvent", sendEmailWhenProductIsCreatedHandler);
        eventDispatcher.unregisterAll();
        assertEquals(0, eventDispatcher.getEventHandlers().size());
    }


    @Test
    void shouldNotifyAllEventHandlers() {
        eventDispatcher.register("ProductCreatedEvent", sendEmailWhenProductIsCreatedHandler);

        Product product = new Product(UUID.randomUUID(), "name", 100);
        var event = new ProductCreatedEvent(product);

        eventDispatcher.notify(event);

        Mockito.verify(sendEmailWhenProductIsCreatedHandler).handle(any());
    }
}