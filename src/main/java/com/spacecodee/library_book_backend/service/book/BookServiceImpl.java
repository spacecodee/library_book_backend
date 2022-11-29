package com.spacecodee.library_book_backend.service.book;

import com.spacecodee.library_book_backend.component.ExceptionShortComponent;
import com.spacecodee.library_book_backend.exceptions.NotAddSqlException;
import com.spacecodee.library_book_backend.exceptions.NotDeleteSqlException;
import com.spacecodee.library_book_backend.exceptions.NotUpdateSqlException;
import com.spacecodee.library_book_backend.mappers.rating.book.IRatingBookKeyMapper;
import com.spacecodee.library_book_backend.model.dto.book.BookAndCategoryDto;
import com.spacecodee.library_book_backend.model.dto.book.BookAndRatingPromedioDto;
import com.spacecodee.library_book_backend.model.dto.book.ShowBookDto;
import com.spacecodee.library_book_backend.model.vo.book.BookVo;
import com.spacecodee.library_book_backend.service.category.book.CategoryBookServiceImpl;
import com.spacecodee.library_book_backend.service.rating.book.RatingBookService;
import com.spacecodee.library_book_backend.service.user.client.UserClientService;
import com.spacecodee.library_book_backend.utils.Utils;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl {

    private final CategoryBookServiceImpl categoryBookService;
    private final RatingBookService ratingBookService;
    private final UserClientService userClientService;
    private final BookService bookService;
    private final ExceptionShortComponent exceptionShortComponent;

    private static final Logger LOGGER = LoggerFactory.getLogger(BookServiceImpl.class);
    private static final String GET_BY_ID_ERROR_BOOK = "get.by.id.error.book";

    public BookServiceImpl(CategoryBookServiceImpl categoryBookService, RatingBookService ratingBookService,
                           UserClientService userClientService, BookService bookService,
                           ExceptionShortComponent exceptionShortComponent) {
        this.categoryBookService = categoryBookService;
        this.ratingBookService = ratingBookService;
        this.userClientService = userClientService;
        this.bookService = bookService;
        this.exceptionShortComponent = exceptionShortComponent;
    }

    public List<BookAndCategoryDto> findAll() {
        return this.bookService.findAll();
    }

    public List<BookAndRatingPromedioDto> findByBookNameLikeIgnoreCase(String name) {
        return this.bookService.findByBookNameLikeIgnoreCase(name);
    }

    public BookAndCategoryDto getById(String lang, int id) {
        return this.bookService
                .getById(id)
                .orElseThrow(() -> this.exceptionShortComponent.notFound(
                        BookServiceImpl.GET_BY_ID_ERROR_BOOK, lang));
    }

    public ShowBookDto getByBookAndClientId(String lang, int bookId, String username) {
        var clientId = this.userClientService.getUserClientIdByUsername(username);
        var rating = this.ratingBookService
                .getRatingById(IRatingBookKeyMapper.INSTANCE.toDto(clientId, bookId)).orElse(0F);
        var globalRating = this.ratingBookService.getPromedioByBookId(bookId).orElse(0F);
        var dto = this.bookService
                .getByBookAndClientId(bookId)
                .orElseThrow(() -> this.exceptionShortComponent
                        .notFound(GET_BY_ID_ERROR_BOOK, lang));
        dto.setRating(rating);
        dto.setGlobalRating(globalRating);
        return dto;
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

    public void add(String lang, @NotNull BookVo vo) {
        this.existByName(lang, vo.getName());
        var category = this.categoryBookService.getById(lang, vo.getCategoryBookVo().getId());
        vo.setCategoryBookVo(category);
        try {
            this.bookService.add(vo);
        } catch (NotAddSqlException e) {
            e.printStackTrace(System.err);
            BookServiceImpl.LOGGER.error("error registering: {}", e.getMessage());
            throw this.exceptionShortComponent.notAddSql("add.error.book", lang);
        }
    }

    public void update(String lang, @NotNull BookVo vo) {
        this.noExistById(lang, vo.getId());
        var category = this.categoryBookService.getById(lang, vo.getCategoryBookVo().getId());
        vo.setCategoryBookVo(category);

        try {
            final var books = this.findAll();
            books.forEach(book -> {
                if (book.getName().equalsIgnoreCase(vo.getName())) {
                    if (book.getId() != vo.getId()) {
                        throw this.exceptionShortComponent.existFound("get.by.name.exists.book", lang);
                    }
                }
                else if (book.getId() == vo.getId()
                        || Utils.isNotEqualsName(book.getName(), vo.getName())) {
                    this.bookService.update(vo);
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
}
