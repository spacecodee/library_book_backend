package com.spacecodee.library_book_backend.service.generics;

import com.spacecodee.library_book_backend.exceptions.NotAddSqlException;
import com.spacecodee.library_book_backend.exceptions.NotDeleteSqlException;
import com.spacecodee.library_book_backend.exceptions.NotUpdateSqlException;

import java.util.List;
import java.util.Optional;

public interface IGenericService<L, A, U> {

    List<L> getAll();

    Optional<L> getById(int id);

    Optional<L> getByName(String name);

    boolean existById(int id);

    boolean existByName(String name);

    void add(A dto) throws NotAddSqlException;

    void update(U dto) throws NotUpdateSqlException;

    void delete(int id) throws NotDeleteSqlException;
}
