package com.spacecodee.library_book_backend.service.category.book;

import com.spacecodee.library_book_backend.model.dto.category.book.CategoryBookAndBookDto;
import com.spacecodee.library_book_backend.model.dto.category.book.CategoryBookDto;
import com.spacecodee.library_book_backend.model.vo.category.book.CategoryBookVo;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ICategoryBookService {

    //dto for user into system
    List<CategoryBookDto> getAll();

    Optional<CategoryBookDto> getByCategoryId(int id);

    //dto for clients that interact with the system
    @Transactional(readOnly = true, rollbackFor = SQLException.class)
    List<CategoryBookAndBookDto> findAll();

    @Transactional(readOnly = true, rollbackFor = SQLException.class)
    Optional<CategoryBookAndBookDto> getByIdCategoryBook(int id);

    //validations about exist category
    boolean existByCategoryId(int id);

    boolean existByCategoryName(String name);

    //vo
    void add(CategoryBookVo dto) throws SQLException;

    void update(CategoryBookVo dto) throws SQLException;

    void delete(int id) throws SQLException;
}
