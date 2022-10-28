package com.spacecodee.library_book_backend.service.category.book;

import com.spacecodee.library_book_backend.component.ExceptionShortComponent;
import com.spacecodee.library_book_backend.dto.category.book.CategoryBookADto;
import com.spacecodee.library_book_backend.dto.category.book.CategoryBookLDto;
import com.spacecodee.library_book_backend.dto.category.book.CategoryBookUDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryBookServiceImpl {

    private final CategoryBookService categoryBookService;
    private final ExceptionShortComponent exceptionShortComponent;

    private static final String GET_BY_ID_ERROR_CATEGORY_BOOK = "get.by.id.error.category.book";

    public CategoryBookServiceImpl(CategoryBookService categoryBookService,
                                   ExceptionShortComponent exceptionShortComponent) {
        this.categoryBookService = categoryBookService;
        this.exceptionShortComponent = exceptionShortComponent;
    }

    public List<CategoryBookLDto> getAll() {
        return this.categoryBookService.getAll();
    }

    public CategoryBookLDto getById(String lang, int id) {
        return this.categoryBookService
                .getById(id)
                .orElseThrow(() -> this.exceptionShortComponent.notFound(
                        CategoryBookServiceImpl.GET_BY_ID_ERROR_CATEGORY_BOOK, lang));
    }

    public CategoryBookLDto getByName(String lang, String name) {
        return this.categoryBookService
                .getByName(name)
                .orElseThrow(() -> this.exceptionShortComponent.notFound("get.by.name.error.category.book", lang));
    }

    public void existById(String lang, int id) {
        if (this.categoryBookService.existById(id)) {
            throw this.exceptionShortComponent.existFound("get.by.id.exists.category.book", lang);
        }
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

    public void add(CategoryBookADto dto) {
        this.categoryBookService.add(dto);
    }

    public void update(CategoryBookUDto dto) {
        List<CategoryBookLDto> categories = this.categoryBookService.getAll();
        categories.forEach(categoryBookLDto -> {
            if (categoryBookLDto.getId() == dto.getId()
                    || this.isNotEqualsName(categoryBookLDto.getName(), dto.getName())) {
                this.categoryBookService.update(dto);
            }
        });
    }

    private boolean isNotEqualsName(String nameFromDto, String name) {
        return !nameFromDto.equalsIgnoreCase(name);
    }

    public void delete(String lang, int id) {
        this.noExistById(lang, id);
        this.categoryBookService.delete(id);
    }
}
