package com.spacecodee.library_book_backend.service;

import java.util.List;
import java.util.Optional;

public interface IGenericService<L, A, U> {

    List<L> getAll();

    Optional<L> getById(int id);

    Optional<L> getByName(String name);

    boolean existById(int id);

    boolean existByName(String name);

    void add(A dto);

    void update(U dto);

    void delete(int id);
}
