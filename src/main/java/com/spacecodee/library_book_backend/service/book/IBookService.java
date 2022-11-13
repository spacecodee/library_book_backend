package com.spacecodee.library_book_backend.service.book;

import com.spacecodee.library_book_backend.core.service.core.IExistByService;
import com.spacecodee.library_book_backend.model.dto.book.BookAndCategoryDto;
import com.spacecodee.library_book_backend.model.dto.book.ShowBookDto;
import com.spacecodee.library_book_backend.model.vo.book.BookVo;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IBookService extends IExistByService {

    //clients and users
    @Transactional(readOnly = true, rollbackFor = SQLException.class)
    List<BookAndCategoryDto> findAll();

    Optional<BookAndCategoryDto> getById(int id);

    //to clients
    @Transactional(readOnly = true, rollbackFor = SQLException.class)
    Optional<ShowBookDto> getByBookAndClientId(int bookId);

    @Override
    boolean existById(int id);

    @Override
    boolean existByName(String name);

    void add(BookVo dto) throws SQLException;

    void update(BookVo dto) throws SQLException;

    void delete(int id) throws SQLException;
}
