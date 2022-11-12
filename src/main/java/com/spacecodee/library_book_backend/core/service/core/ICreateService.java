package com.spacecodee.library_book_backend.core.service.core;

import com.spacecodee.library_book_backend.exceptions.NotAddSqlException;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

public interface ICreateService<C> {

    @Transactional(rollbackFor = SQLException.class)
    void add(C dto) throws NotAddSqlException;
}
