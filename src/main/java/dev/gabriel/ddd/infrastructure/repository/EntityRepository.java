package dev.gabriel.ddd.infrastructure.repository;

import java.util.UUID;

public interface EntityRepository<T> {

    void create(T entity);

    void update(T entity);

    T find(UUID id);

    T[] findAll();
}
