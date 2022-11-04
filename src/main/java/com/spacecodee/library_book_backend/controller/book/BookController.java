package com.spacecodee.library_book_backend.controller.book;

import com.spacecodee.library_book_backend.component.ExceptionShortComponent;
import com.spacecodee.library_book_backend.component.MessageUtilComponent;
import com.spacecodee.library_book_backend.controller.generics.IAllController;
import com.spacecodee.library_book_backend.dto.book.BookLDto;
import com.spacecodee.library_book_backend.dto.http.HttpResponseApi;
import com.spacecodee.library_book_backend.dto.http.HttpResponseApiMsg;
import com.spacecodee.library_book_backend.exceptions.NotAddSqlException;
import com.spacecodee.library_book_backend.exceptions.NotDeleteSqlException;
import com.spacecodee.library_book_backend.exceptions.NotUpdateSqlException;
import com.spacecodee.library_book_backend.service.book.BookServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/book")
public class BookController implements IAllController<BookLDto, BookLDto, BookLDto> {

    private final BookServiceImpl bookService;
    private final ExceptionShortComponent exceptionShortComponent;
    private final MessageUtilComponent messageUtilComponent;

    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    public BookController(BookServiceImpl bookService, ExceptionShortComponent exceptionShortComponent,
                          MessageUtilComponent messageUtilComponent) {
        this.bookService = bookService;
        this.exceptionShortComponent = exceptionShortComponent;
        this.messageUtilComponent = messageUtilComponent;
    }

    @Override
    public ResponseEntity<HttpResponseApiMsg<List<BookLDto>>> list(String lang) {
        final HttpResponseApiMsg<List<BookLDto>> httpResponseApiMsg = new HttpResponseApiMsg<>();
        httpResponseApiMsg.setData(this.bookService.getAll());

        if (httpResponseApiMsg.getData().isEmpty()) {
            httpResponseApiMsg.setMessage(
                    this.messageUtilComponent.getMessage("get.all.no.content.book", lang));
            httpResponseApiMsg.setHttpStatus(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(httpResponseApiMsg, HttpStatus.OK);
        }

        httpResponseApiMsg.setMessage(this.messageUtilComponent.getMessage("get.all.success.book", lang));
        httpResponseApiMsg.setHttpStatus(HttpStatus.OK);
        return new ResponseEntity<>(httpResponseApiMsg, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpResponseApiMsg<BookLDto>> getById(String lang, int id) {
        final HttpResponseApiMsg<BookLDto> httpResponseApiMsg = new HttpResponseApiMsg<>();
        httpResponseApiMsg.setData(this.bookService.getById(lang, id));
        httpResponseApiMsg.setMessage(this.messageUtilComponent.getMessage("get.by.id.success.book", lang));
        httpResponseApiMsg.setHttpStatus(HttpStatus.OK);
        return new ResponseEntity<>(httpResponseApiMsg, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpResponseApi> add(String lang, BookLDto dto) {
        HttpResponseApi httpResponseApiMsg = new HttpResponseApi();

        try {
            this.bookService.add(lang, dto);
            httpResponseApiMsg.setMessage(this.messageUtilComponent.getMessage("add.success.book", lang));
            httpResponseApiMsg.setHttpStatus(HttpStatus.OK);
        } catch (NotAddSqlException exception) {
            BookController.LOGGER.error("error registering: {}", exception.getMessage());
            throw this.exceptionShortComponent.notAddSql("add.error.book", lang);
        }

        return new ResponseEntity<>(httpResponseApiMsg, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpResponseApi> update(String lang, int id, BookLDto dto) {
        HttpResponseApi httpResponseApiMsg = new HttpResponseApi();

        dto.setId(id);
        try {
            this.bookService.update(lang, dto);
            httpResponseApiMsg.setMessage(this.messageUtilComponent.getMessage("update.success.book", lang));
            httpResponseApiMsg.setHttpStatus(HttpStatus.OK);
        } catch (NotUpdateSqlException exception) {
            BookController.LOGGER.error("error updating: {}", exception.getMessage());
            throw this.exceptionShortComponent.noUpdateSql("update.error.book", lang);
        }

        return new ResponseEntity<>(httpResponseApiMsg, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpResponseApi> delete(String lang, int id) {
        HttpResponseApi httpResponseApiMsg = new HttpResponseApi();

        try {
            this.bookService.delete(lang, id);
            httpResponseApiMsg.setMessage(this.messageUtilComponent.getMessage("delete.success.book", lang));
            httpResponseApiMsg.setHttpStatus(HttpStatus.OK);
        } catch (NotDeleteSqlException exception) {
            BookController.LOGGER.error("error deleting: {}", exception.getMessage());
            throw this.exceptionShortComponent.notAddSql("delete.error.book", lang);
        }

        return new ResponseEntity<>(httpResponseApiMsg, HttpStatus.OK);
    }
}
