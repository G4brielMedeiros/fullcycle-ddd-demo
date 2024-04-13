package dev.gabriel.ddd.domain.event.shared;

import java.time.LocalDateTime;

public interface Event {
    LocalDateTime getCreatedAt();

    Object getEventData();
}
