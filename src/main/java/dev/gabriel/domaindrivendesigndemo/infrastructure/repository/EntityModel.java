package dev.gabriel.domaindrivendesigndemo.infrastructure.repository;

public interface EntityModel<T> {
    T toEntity();
}
