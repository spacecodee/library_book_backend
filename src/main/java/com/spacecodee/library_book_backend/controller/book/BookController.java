package com.spacecodee.library_book_backend.controller.book;

import com.spacecodee.library_book_backend.component.MessageUtilComponent;
import com.spacecodee.library_book_backend.dto.http.HttpResponseApi;
import com.spacecodee.library_book_backend.dto.http.HttpResponseApiMsg;
import com.spacecodee.library_book_backend.exceptions.NotAddSqlException;
import com.spacecodee.library_book_backend.exceptions.NotDeleteSqlException;
import com.spacecodee.library_book_backend.exceptions.NotUpdateSqlException;
import com.spacecodee.library_book_backend.model.dto.book.BookAndCategoryDto;
import com.spacecodee.library_book_backend.model.dto.book.ShowBookDto;
import com.spacecodee.library_book_backend.model.vo.book.BookVo;
import com.spacecodee.library_book_backend.service.book.BookServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/book")
public class BookController implements IBookController {

    private final BookServiceImpl bookService;
    private final MessageUtilComponent messageUtilComponent;

    public BookController(BookServiceImpl bookService,
                          MessageUtilComponent messageUtilComponent) {
        this.bookService = bookService;
        this.messageUtilComponent = messageUtilComponent;
    }

    @Override
    public ResponseEntity<HttpResponseApiMsg<List<BookAndCategoryDto>>> findAll(String lang) {
        final var response = new HttpResponseApiMsg<List<BookAndCategoryDto>>();
        response.setData(this.bookService.findAll());
        if (response.getData().isEmpty()) {
            response.setMessage(this.messageUtilComponent.getMessage("get.all.no.content.book", lang));
            response.setHttpStatus(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        response.setMessage(this.messageUtilComponent.getMessage("get.all.success.book", lang));
        response.setHttpStatus(HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpResponseApiMsg<BookAndCategoryDto>> getById(String lang, int id) {
        final var response = new HttpResponseApiMsg<BookAndCategoryDto>();
        response.setData(this.bookService.getById(lang, id));
        response.setMessage(this.messageUtilComponent.getMessage("get.by.id.success.book", lang));
        response.setHttpStatus(HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpResponseApiMsg<ShowBookDto>> getByBookAndClientId(String lang, int bookId, int clientId) {
        final var response = new HttpResponseApiMsg<ShowBookDto>();
        response.setData(this.bookService.getByBookAndClientId(lang, bookId, clientId));
        response.setMessage(this.messageUtilComponent.getMessage("get.by.id.success.book", lang));
        response.setHttpStatus(HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpResponseApi> add(String lang, BookVo dto) throws NotAddSqlException {
        final var response = new HttpResponseApi();
        this.bookService.add(lang, dto);
        response.setMessage(this.messageUtilComponent.getMessage("add.success.book", lang));
        response.setHttpStatus(HttpStatus.OK);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpResponseApi> update(String lang, int id, BookVo dto)
            throws NotUpdateSqlException {
        final var response = new HttpResponseApi();

        dto.setId(id);
        this.bookService.update(lang, dto);
        response.setMessage(this.messageUtilComponent.getMessage("update.success.book", lang));
        response.setHttpStatus(HttpStatus.OK);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpResponseApi> delete(String lang, int id) throws NotDeleteSqlException {
        final var response = new HttpResponseApi();

        this.bookService.delete(lang, id);
        response.setMessage(this.messageUtilComponent.getMessage("delete.success.book", lang));
        response.setHttpStatus(HttpStatus.OK);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
