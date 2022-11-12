package com.spacecodee.library_book_backend.controller.book;

import com.spacecodee.library_book_backend.component.MessageUtilComponent;
import com.spacecodee.library_book_backend.core.controller.ICRUDController;
import com.spacecodee.library_book_backend.dto.book.action.BookDto;
import com.spacecodee.library_book_backend.dto.book.action.BookFlatADto;
import com.spacecodee.library_book_backend.dto.book.action.BookFlatUDto;
import com.spacecodee.library_book_backend.dto.book.flat.BookDtoFlat;
import com.spacecodee.library_book_backend.dto.http.HttpResponseApi;
import com.spacecodee.library_book_backend.dto.http.HttpResponseApiMsg;
import com.spacecodee.library_book_backend.entity.book.BookAllFlatDto;
import com.spacecodee.library_book_backend.service.book.BookServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/book")
public class BookController implements ICRUDController<BookFlatADto, BookDto, BookFlatUDto, Integer> {

    private final BookServiceImpl bookService;
    private final MessageUtilComponent messageUtilComponent;

    public BookController(BookServiceImpl bookService,
                          MessageUtilComponent messageUtilComponent) {
        this.bookService = bookService;
        this.messageUtilComponent = messageUtilComponent;
    }


    @ApiOperation(value = "List books Endpoint", notes = "This Endpoint return a list of books")
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

    @GetMapping("/test")
    public ResponseEntity<HttpResponseApiMsg<List<BookAllFlatDto>>> listTest() {
        final HttpResponseApiMsg<List<BookAllFlatDto>> httpResponseApiMsg = new HttpResponseApiMsg<>();
        httpResponseApiMsg.setData(this.bookService.getAllTest());

        httpResponseApiMsg.setMessage(this.messageUtilComponent.getMessage("get.all.success.book", "eng"));
        httpResponseApiMsg.setHttpStatus(HttpStatus.OK);
        return new ResponseEntity<>(httpResponseApiMsg, HttpStatus.OK);
    }

    @ApiOperation(value = "Get a book by Id Endpoint", notes = "This Endpoint return a book by id")
    @Override
    public ResponseEntity<HttpResponseApiMsg<BookDto>> getById(String lang, int id) {
        System.out.println("lang = " + lang);
        final HttpResponseApiMsg<BookDto> httpResponseApiMsg = new HttpResponseApiMsg<>();
        httpResponseApiMsg.setData(this.bookService.getById(lang, id));
        httpResponseApiMsg.setMessage(this.messageUtilComponent.getMessage("get.by.id.success.book", lang));
        httpResponseApiMsg.setHttpStatus(HttpStatus.OK);
        return new ResponseEntity<>(httpResponseApiMsg, HttpStatus.OK);
    }

    @GetMapping("/get-by/{bookId}")
    public ResponseEntity<HttpResponseApiMsg<BookDtoFlat>> getByBookId(
            @RequestParam(defaultValue = "en", required = false) String lang,
            @RequestParam() int clientId,
            @PathVariable() int bookId) {
        final HttpResponseApiMsg<BookDtoFlat> httpResponseApiMsg = new HttpResponseApiMsg<>();
        httpResponseApiMsg.setData(this.bookService.getByBookId(lang, bookId, clientId));
        httpResponseApiMsg.setMessage(this.messageUtilComponent.getMessage("get.by.id.success.book", lang));
        httpResponseApiMsg.setHttpStatus(HttpStatus.OK);
        return new ResponseEntity<>(httpResponseApiMsg, HttpStatus.OK);
    }

    @ApiOperation(value = "Add Book Endpoint", notes = "Here you'll able to add a book to this app", authorizations = {
            @io.swagger.annotations.Authorization(value = "Bearer")
    })
    @Override
    public ResponseEntity<HttpResponseApi> add(String lang, BookFlatADto dto) {
        HttpResponseApi httpResponseApiMsg = new HttpResponseApi();

        this.bookService.add(lang, dto);
        httpResponseApiMsg.setMessage(this.messageUtilComponent.getMessage("add.success.book", lang));
        httpResponseApiMsg.setHttpStatus(HttpStatus.OK);

        return new ResponseEntity<>(httpResponseApiMsg, HttpStatus.OK);
    }

    @ApiOperation(value = "Update Book Endpoint", notes = "Here you'll able to update a book to this app",
            authorizations = {
                    @io.swagger.annotations.Authorization(value = "Bearer")
            })
    @Override
    public ResponseEntity<HttpResponseApi> update(String lang, int id, BookFlatUDto dto) {
        HttpResponseApi httpResponseApiMsg = new HttpResponseApi();

        dto.setId(id);
        this.bookService.update(lang, dto);
        httpResponseApiMsg.setMessage(this.messageUtilComponent.getMessage("update.success.book", lang));
        httpResponseApiMsg.setHttpStatus(HttpStatus.OK);

        return new ResponseEntity<>(httpResponseApiMsg, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete Book Endpoint", notes = "Here you'll able to delete a book to this app",
            authorizations = {
                    @io.swagger.annotations.Authorization(value = "Bearer")
            })
    @Override
    public ResponseEntity<HttpResponseApi> delete(String lang, Integer id) {
        HttpResponseApi httpResponseApiMsg = new HttpResponseApi();

        this.bookService.delete(lang, id);
        httpResponseApiMsg.setMessage(this.messageUtilComponent.getMessage("delete.success.book", lang));
        httpResponseApiMsg.setHttpStatus(HttpStatus.OK);

        return new ResponseEntity<>(httpResponseApiMsg, HttpStatus.OK);
    }
}
