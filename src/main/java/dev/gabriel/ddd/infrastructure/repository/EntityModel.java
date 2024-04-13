package dev.gabriel.ddd.infrastructure.repository;

public interface EntityModel<T> {
    T toEntity();
}
