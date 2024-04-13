package dev.gabriel.ddd.domain._shared.event;

public interface EventHandler<T extends Event> {
    void handle(T event);
}
