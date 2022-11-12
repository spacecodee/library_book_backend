package com.spacecodee.library_book_backend.core.service.core;

import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

public interface IDeleteService<D> {

    @Transactional(rollbackFor = SQLException.class)
    void delete(D id);
}
