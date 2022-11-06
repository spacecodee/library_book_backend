package com.spacecodee.library_book_backend.controller.book;

import com.spacecodee.library_book_backend.component.MessageUtilComponent;
import com.spacecodee.library_book_backend.controller.generics.IAllController;
import com.spacecodee.library_book_backend.dto.book.BookDto;
import com.spacecodee.library_book_backend.dto.book.flat.BookFlatADto;
import com.spacecodee.library_book_backend.dto.book.flat.BookFlatUDto;
import com.spacecodee.library_book_backend.dto.http.HttpResponseApi;
import com.spacecodee.library_book_backend.dto.http.HttpResponseApiMsg;
import com.spacecodee.library_book_backend.service.book.BookServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/book")
public class BookController implements IAllController<BookDto, BookFlatADto, BookFlatUDto> {

    private final BookServiceImpl bookService;
    private final MessageUtilComponent messageUtilComponent;

    public BookController(BookServiceImpl bookService,
                          MessageUtilComponent messageUtilComponent) {
        this.bookService = bookService;
        this.messageUtilComponent = messageUtilComponent;
    }

    @Override
    public ResponseEntity<HttpResponseApiMsg<List<BookDto>>> list(String lang) {
        final HttpResponseApiMsg<List<BookDto>> httpResponseApiMsg = new HttpResponseApiMsg<>();
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
    public ResponseEntity<HttpResponseApiMsg<BookDto>> getById(String lang, int id) {
        final HttpResponseApiMsg<BookDto> httpResponseApiMsg = new HttpResponseApiMsg<>();
        httpResponseApiMsg.setData(this.bookService.getById(lang, id));
        httpResponseApiMsg.setMessage(this.messageUtilComponent.getMessage("get.by.id.success.book", lang));
        httpResponseApiMsg.setHttpStatus(HttpStatus.OK);
        return new ResponseEntity<>(httpResponseApiMsg, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpResponseApi> add(String lang, BookFlatADto dto) {
        HttpResponseApi httpResponseApiMsg = new HttpResponseApi();

        this.bookService.add(lang, dto);
        httpResponseApiMsg.setMessage(this.messageUtilComponent.getMessage("add.success.book", lang));
        httpResponseApiMsg.setHttpStatus(HttpStatus.OK);

        return new ResponseEntity<>(httpResponseApiMsg, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpResponseApi> update(String lang, int id, BookFlatUDto dto) {
        HttpResponseApi httpResponseApiMsg = new HttpResponseApi();

        dto.setId(id);
        this.bookService.update(lang, dto);
        httpResponseApiMsg.setMessage(this.messageUtilComponent.getMessage("update.success.book", lang));
        httpResponseApiMsg.setHttpStatus(HttpStatus.OK);

        return new ResponseEntity<>(httpResponseApiMsg, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpResponseApi> delete(String lang, int id) {
        HttpResponseApi httpResponseApiMsg = new HttpResponseApi();

        this.bookService.delete(lang, id);
        httpResponseApiMsg.setMessage(this.messageUtilComponent.getMessage("delete.success.book", lang));
        httpResponseApiMsg.setHttpStatus(HttpStatus.OK);

        return new ResponseEntity<>(httpResponseApiMsg, HttpStatus.OK);
    }
}
