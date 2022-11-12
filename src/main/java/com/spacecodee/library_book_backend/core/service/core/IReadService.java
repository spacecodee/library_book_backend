package com.spacecodee.library_book_backend.core.service.core;

import java.util.List;
import java.util.Optional;

public interface IReadService<R> {

    default List<R> getAll() {
        return List.of();
    }

    default Optional<R> getById(int id) {
        return Optional.empty();
    }

    default Optional<R> getByName(String name) {
        return Optional.empty();
    }

    default boolean existById(int id) {
        return true;
    }

    default boolean existByName(String name) {
        return true;
    }
}
