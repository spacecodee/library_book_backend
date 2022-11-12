package com.spacecodee.library_book_backend.service.book;

import com.spacecodee.library_book_backend.component.ExceptionShortComponent;
import com.spacecodee.library_book_backend.dto.book.action.BookDto;
import com.spacecodee.library_book_backend.dto.book.action.BookFlatADto;
import com.spacecodee.library_book_backend.dto.book.action.BookFlatUDto;
import com.spacecodee.library_book_backend.dto.book.flat.BookDtoFlat;
import com.spacecodee.library_book_backend.entity.book.BookAllFlatDto;
import com.spacecodee.library_book_backend.exceptions.NotAddSqlException;
import com.spacecodee.library_book_backend.exceptions.NotDeleteSqlException;
import com.spacecodee.library_book_backend.exceptions.NotUpdateSqlException;
import com.spacecodee.library_book_backend.mappers.book.IBookEntityMapper;
import com.spacecodee.library_book_backend.service.category.book.CategoryBookServiceImpl;
import com.spacecodee.library_book_backend.service.rating.book.RatingBookService;
import com.spacecodee.library_book_backend.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl {

    private final CategoryBookServiceImpl categoryBookService;

    private final RatingBookService ratingBookService;
    private final BookService bookService;
    private final ExceptionShortComponent exceptionShortComponent;

    private static final Logger LOGGER = LoggerFactory.getLogger(BookServiceImpl.class);
    private static final String GET_BY_ID_ERROR_BOOK = "get.by.id.error.book";

    public BookServiceImpl(CategoryBookServiceImpl categoryBookService, RatingBookService ratingBookService,
                           BookService bookService,
                           ExceptionShortComponent exceptionShortComponent) {
        this.categoryBookService = categoryBookService;
        this.ratingBookService = ratingBookService;
        this.bookService = bookService;
        this.exceptionShortComponent = exceptionShortComponent;
    }

    public List<BookDto> getAll() {
        return this.bookService.getAll();
    }

    public List<BookAllFlatDto> getAllTest() {
        return this.bookService.getAllTest();
    }

    public BookDto getById(String lang, int id) {
        return this.bookService
                .getById(id)
                .orElseThrow(() -> this.exceptionShortComponent.notFound(
                        BookServiceImpl.GET_BY_ID_ERROR_BOOK, lang));
    }

    public BookDtoFlat getByBookId(String lang, int bookId, int clientId) {
        var rating = this.ratingBookService.getRatingByBookId(bookId, clientId);
        var promedio = this.bookService
                .getByBookIdAndClientId(bookId, clientId)
                .orElseThrow(() -> this.exceptionShortComponent.notFound(
                        BookServiceImpl.GET_BY_ID_ERROR_BOOK, lang));
        promedio.setRating(rating);
        return promedio;
    }

    public BookDto getByName(String lang, String name) {
        return this.bookService
                .getByName(name)
                .orElseThrow(() -> this.exceptionShortComponent.notFound("get.by.name.error.book", lang));
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

    public void add(String lang, BookFlatADto dto) {
        this.existByName(lang, dto.getName());
        BookDto book = this.getBookLDto(lang, dto);

        try {
            this.bookService.add(book);
        } catch (NotAddSqlException e) {
            e.printStackTrace(System.err);
            BookServiceImpl.LOGGER.error("error registering: {}", e.getMessage());
            throw this.exceptionShortComponent.notAddSql("add.error.book", lang);
        }
    }

    public void update(String lang, BookFlatUDto dto) {
        this.noExistById(lang, dto.getId());
        BookDto book = this.getBookLDto(lang, dto);

        try {
            List<BookDto> books = this.bookService.getAll();
            books.forEach(bookLDto -> {
                if (bookLDto.getName().equalsIgnoreCase(dto.getName())) {
                    if (bookLDto.getId() != dto.getId()) {
                        throw this.exceptionShortComponent.existFound("get.by.name.exists.book", lang);
                    }
                }
                else if (bookLDto.getId() == dto.getId()
                        || Utils.isNotEqualsName(bookLDto.getName(), dto.getName())) {
                    this.bookService.update(book);
                }
            });
        } catch (NotUpdateSqlException exception) {
            exception.printStackTrace(System.err);
            BookServiceImpl.LOGGER.error("error updating: {}", exception.getMessage());
            throw this.exceptionShortComponent.noUpdateSql("update.error.book", lang);
        }
    }

    public void delete(String lang, int id) {
        this.noExistById(lang, id);
        try {
            this.bookService.delete(id);
        } catch (NotDeleteSqlException exception) {
            exception.printStackTrace(System.err);
            BookServiceImpl.LOGGER.error("error deleting: {}", exception.getMessage());
            throw this.exceptionShortComponent.notAddSql("delete.error.book", lang);
        }
    }

    private BookDto getBookLDto(String lang, BookFlatADto dto) {
        var category = this.categoryBookService.getById(lang, dto.getCategoryId());
        var book = IBookEntityMapper.INSTANCE.toLDto(dto);
        book.setCategoryBookDto(category);
        return book;
    }

    private BookDto getBookLDto(String lang, BookFlatUDto dto) {
        var category = this.categoryBookService.getById(lang, dto.getCategoryId());
        var book = IBookEntityMapper.INSTANCE.toLDto(dto);
        book.setCategoryBookDto(category);
        return book;
    }
}
