package dev.gabriel.ddd.domain._shared.event;

public interface EventDispatcher {

    void notify(Event event);

    void register(String eventName, EventHandler<? extends Event> handler);

    void unregister(String eventName, EventHandler<? extends Event> handler);

    void unregisterAll();
}
