package dev.gabriel.ddd.domain.event.shared;

public interface EventDispatcher {

    void notify(Event event);

    void register(String eventName, EventHandler<? extends Event> handler);

    void unregister(String eventName, EventHandler<? extends Event> handler);

    void unregisterAll();
}
