package com.spacecodee.library_book_backend.core.service.core;

import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

public interface IUpdateService<U> {

    @Transactional(rollbackFor = SQLException.class)
    default void update(U dto) {
    }

    @Transactional(rollbackFor = SQLException.class)
    default void update(int id) {
    }
}
