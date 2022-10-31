package com.spacecodee.library_book_backend.service.book;

import com.spacecodee.library_book_backend.component.ExceptionShortComponent;
import com.spacecodee.library_book_backend.dto.book.BookLDto;
import com.spacecodee.library_book_backend.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl {

    private final BookService bookService;
    private final ExceptionShortComponent exceptionShortComponent;

    private static final String GET_BY_ID_ERROR_BOOK = "get.by.id.error.book";

    public BookServiceImpl(BookService bookService, ExceptionShortComponent exceptionShortComponent) {
        this.bookService = bookService;
        this.exceptionShortComponent = exceptionShortComponent;
    }

    public List<BookLDto> getAll() {
        return this.bookService.getAll();
    }

    public BookLDto getById(String lang, int id) {
        return this.bookService
                .getById(id)
                .orElseThrow(() -> this.exceptionShortComponent.notFound(
                        BookServiceImpl.GET_BY_ID_ERROR_BOOK, lang));
    }

    public BookLDto getByName(String lang, String name) {
        return this.bookService
                .getByName(name)
                .orElseThrow(() -> this.exceptionShortComponent.notFound("get.by.name.error.book", lang));
    }

    public void existById(String lang, int id) {
        if (this.bookService.existById(id)) {
            throw this.exceptionShortComponent.existFound("get.by.id.exists.book", lang);
        }
    }

    public void noExistById(String lang, int id) {
        if (!this.bookService.existById(id)) {
            throw this.exceptionShortComponent.notFound(BookServiceImpl.GET_BY_ID_ERROR_BOOK, lang);
        }
    }

    public void existByName(String lang, String name) {
        if (this.bookService.existByName(name)) {
            throw this.exceptionShortComponent.existFound("get.by.name.exists.book", lang);
        }
    }

    public void add(String lang, BookLDto dto) {
        this.existByName(lang, dto.getName());
        this.bookService.add(dto);
    }

    public void update(String lang, BookLDto dto) {
        this.noExistById(lang, dto.getId());

        List<BookLDto> books = this.bookService.getAll();
        books.forEach(bookLDto -> {
            if (bookLDto.getName().equalsIgnoreCase(dto.getName())) {
                if (bookLDto.getId() != dto.getId()) {
                    throw this.exceptionShortComponent.existFound("get.by.name.exists.book", lang);
                }
            }
            else if (bookLDto.getId() == dto.getId()
                    || Utils.isNotEqualsName(bookLDto.getName(), dto.getName())) {
                this.bookService.update(dto);
            }
        });
    }

    public void delete(String lang, int id) {
        this.noExistById(lang, id);
        this.bookService.delete(id);
    }
}
