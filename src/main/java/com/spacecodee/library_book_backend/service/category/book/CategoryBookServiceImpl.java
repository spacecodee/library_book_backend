package com.spacecodee.library_book_backend.service.category.book;

import com.spacecodee.library_book_backend.component.ExceptionShortComponent;
import com.spacecodee.library_book_backend.exceptions.NotAddSqlException;
import com.spacecodee.library_book_backend.exceptions.NotDeleteSqlException;
import com.spacecodee.library_book_backend.exceptions.NotUpdateSqlException;
import com.spacecodee.library_book_backend.model.dto.category.book.CategoryBookAndBookDto;
import com.spacecodee.library_book_backend.model.dto.category.book.CategoryBookDto;
import com.spacecodee.library_book_backend.model.vo.category.book.CategoryBookVo;
import com.spacecodee.library_book_backend.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryBookServiceImpl {

    private final CategoryBookService categoryBookService;
    private final ExceptionShortComponent exceptionShortComponent;

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryBookServiceImpl.class);
    private static final String GET_BY_ID_ERROR_CATEGORY_BOOK = "get.by.id.error.category.book";

    public CategoryBookServiceImpl(CategoryBookService categoryBookService,
                                   ExceptionShortComponent exceptionShortComponent) {
        this.categoryBookService = categoryBookService;
        this.exceptionShortComponent = exceptionShortComponent;
    }

    public List<CategoryBookDto> getAll() {
        return this.categoryBookService.getAll();
    }

    public CategoryBookDto getByCategoryId(String lang, int id) {
        return this.categoryBookService
                .getByCategoryId(id)
                .orElseThrow(() -> this.exceptionShortComponent.notFound(
                        CategoryBookServiceImpl.GET_BY_ID_ERROR_CATEGORY_BOOK, lang));
    }

    public CategoryBookVo getById(String lang, int id) {
        return this.categoryBookService
                .getById(id)
                .orElseThrow(() -> this.exceptionShortComponent.notFound(
                        CategoryBookServiceImpl.GET_BY_ID_ERROR_CATEGORY_BOOK, lang));
    }

    public List<CategoryBookAndBookDto> findAllBy() {
        return this.categoryBookService.findAll();
    }

    public CategoryBookAndBookDto getByIdCategoryBook(String lang, int id) {
        return this.categoryBookService
                .getByCategoryBookId(id)
                .orElseThrow(() -> this.exceptionShortComponent.notFound(
                        CategoryBookServiceImpl.GET_BY_ID_ERROR_CATEGORY_BOOK, lang));
    }


    public void noExistById(String lang, int id) {
        if (!this.categoryBookService.existById(id)) {
            throw this.exceptionShortComponent.notFound(CategoryBookServiceImpl.GET_BY_ID_ERROR_CATEGORY_BOOK, lang);
        }
    }

    public void existByName(String lang, String name) {
        if (this.categoryBookService.existByName(name)) {
            throw this.exceptionShortComponent.existFound("get.by.name.exists.category.book", lang);
        }
    }

    public void add(String lang, CategoryBookVo dto) {
        this.existByName(lang, dto.getName());
        try {
            this.categoryBookService.add(dto);
        } catch (NotAddSqlException exception) {
            exception.printStackTrace(System.err);
            CategoryBookServiceImpl.LOGGER.error("error registering: {}", exception.getMessage());
            throw this.exceptionShortComponent.notAddSql("add.error.category.book", lang);
        }
    }

    public void update(String lang, CategoryBookVo dto) {
        this.noExistById(lang, dto.getId());

        try {
            List<CategoryBookDto> categories = this.categoryBookService.getAll();
            categories.forEach(categoryBookLDto -> {
                if (categoryBookLDto.getName().equalsIgnoreCase(dto.getName())) {
                    if (categoryBookLDto.getId() != dto.getId()) {
                        throw this.exceptionShortComponent.existFound("get.by.name.exists.category.book", lang);
                    }
                }
                else if (categoryBookLDto.getId() == dto.getId()
                        || Utils.isNotEqualsName(categoryBookLDto.getName(), dto.getName())) {
                    this.categoryBookService.update(dto);
                }
            });
        } catch (NotUpdateSqlException exception) {
            exception.printStackTrace(System.err);
            CategoryBookServiceImpl.LOGGER.error("error updating: {}", exception.getMessage());
            throw this.exceptionShortComponent.noUpdateSql("update.error.category.book", lang);
        }
    }

    public void delete(String lang, int id) {
        this.noExistById(lang, id);
        try {
            this.categoryBookService.delete(id);
        } catch (NotDeleteSqlException exception) {
            exception.printStackTrace(System.err);
            CategoryBookServiceImpl.LOGGER.error("error deleting: {}", exception.getMessage());
            throw this.exceptionShortComponent.notAddSql("delete.error.category.book", lang);
        }
    }
}
