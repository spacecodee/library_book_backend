package com.spacecodee.library_book_backend.controller.rating.book;

import com.spacecodee.library_book_backend.component.MessageUtilComponent;
import com.spacecodee.library_book_backend.model.dto.http.HttpResponseApi;
import com.spacecodee.library_book_backend.model.vo.rating.book.RatingBookVo;
import com.spacecodee.library_book_backend.service.rating.book.RatingBookServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/rating-book")
public class RatingBookController implements IRatingBookController {

    private final RatingBookServiceImpl ratingBookService;
    private final MessageUtilComponent messageUtilComponent;

    public RatingBookController(RatingBookServiceImpl ratingBookService,
                                MessageUtilComponent messageUtilComponent) {
        this.ratingBookService = ratingBookService;
        this.messageUtilComponent = messageUtilComponent;
    }

    @Override
    public ResponseEntity<HttpResponseApi> add(String lang, RatingBookVo dto) {
        HttpResponseApi httpResponseApiMsg = new HttpResponseApi();

        this.ratingBookService.add(lang, dto);
        httpResponseApiMsg.setMessage(this.messageUtilComponent.getMessage("add.success.rating.book", lang));
        httpResponseApiMsg.setHttpStatus(HttpStatus.OK);
        return new ResponseEntity<>(httpResponseApiMsg, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpResponseApi> update(String lang, int bookId, RatingBookVo dto) {
        HttpResponseApi httpResponseApiMsg = new HttpResponseApi();

        dto.setBookId(bookId);
        this.ratingBookService.update(lang, dto);
        httpResponseApiMsg.setMessage(this.messageUtilComponent.getMessage("update.success.rating.book", lang));
        httpResponseApiMsg.setHttpStatus(HttpStatus.OK);
        return new ResponseEntity<>(httpResponseApiMsg, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpResponseApi> delete(String lang, int clientId, int bookId) {
        HttpResponseApi httpResponseApiMsg = new HttpResponseApi();
        this.ratingBookService.delete(lang, clientId, bookId);
        httpResponseApiMsg.setMessage(this.messageUtilComponent.getMessage("delete.success.rating.book", lang));
        httpResponseApiMsg.setHttpStatus(HttpStatus.OK);

        return new ResponseEntity<>(httpResponseApiMsg, HttpStatus.OK);
    }
}
