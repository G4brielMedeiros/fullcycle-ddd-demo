package dev.gabriel.ddd.domain._shared.event;

import java.time.LocalDateTime;

public interface Event {
    LocalDateTime getCreatedAt();

    Object getEventData();
}
