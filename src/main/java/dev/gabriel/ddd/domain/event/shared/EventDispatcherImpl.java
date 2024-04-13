package dev.gabriel.ddd.domain.event.shared;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class EventDispatcherImpl implements EventDispatcher {


    private final Map<String, List<EventHandler>> eventHandlers = new HashMap<>();

    @Override
    public void notify(Event event) {
        var eventName = event.getClass().getSimpleName();
        if (eventHandlers.containsKey(eventName)) {
            this.eventHandlers.get(eventName).forEach(handler -> {
                handler.handle(event);
            });
        }
    }

    @Override
    public void register(String eventName, EventHandler<? extends Event> handler) {
        this.eventHandlers.computeIfAbsent(eventName, k -> new ArrayList<>()).add(handler);
    }

    @Override
    public void unregister(String eventName, EventHandler<? extends Event> handler) {
        this.eventHandlers.get(eventName).remove(handler);
    }

    @Override
    public void unregisterAll() {
        this.eventHandlers.clear();
    }
}
