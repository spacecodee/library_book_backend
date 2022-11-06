package com.spacecodee.library_book_backend.controller.rating.book;

import com.spacecodee.library_book_backend.annotations.IsAuthenticatedAsClient;
import com.spacecodee.library_book_backend.component.MessageUtilComponent;
import com.spacecodee.library_book_backend.core.controller.core.ICreateController;
import com.spacecodee.library_book_backend.dto.http.HttpResponseApi;
import com.spacecodee.library_book_backend.dto.http.HttpResponseApiMsg;
import com.spacecodee.library_book_backend.dto.rating.book.RatingBookDto;
import com.spacecodee.library_book_backend.dto.rating.book.UserRatingBookDto;
import com.spacecodee.library_book_backend.dto.rating.book.UserRatingBookKeyDto;
import com.spacecodee.library_book_backend.service.rating.book.RatingBookServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/rating-book")
public class RatingBookController implements ICreateController<RatingBookDto> {

    private final RatingBookServiceImpl ratingBookService;
    private final MessageUtilComponent messageUtilComponent;

    public RatingBookController(RatingBookServiceImpl ratingBookService, MessageUtilComponent messageUtilComponent) {
        this.ratingBookService = ratingBookService;
        this.messageUtilComponent = messageUtilComponent;
    }

    @IsAuthenticatedAsClient
    @GetMapping()
    public ResponseEntity<HttpResponseApiMsg<UserRatingBookDto>> getById(@RequestParam(defaultValue = "en") String lang,
                                                                         @RequestParam() int clientId,
                                                                         @RequestParam() int bookId) {
        final HttpResponseApiMsg<UserRatingBookDto> httpResponseApiMsg = new HttpResponseApiMsg<>();
        httpResponseApiMsg.setData(this.ratingBookService.getById(lang, new UserRatingBookKeyDto(clientId, bookId)));
        httpResponseApiMsg.setMessage(this.messageUtilComponent.getMessage("get.by.id.success.user.client", lang));
        httpResponseApiMsg.setHttpStatus(HttpStatus.OK);
        return new ResponseEntity<>(httpResponseApiMsg, HttpStatus.OK);
    }

    @IsAuthenticatedAsClient
    @Override
    public ResponseEntity<HttpResponseApi> add(String lang, RatingBookDto dto) {
        HttpResponseApi httpResponseApiMsg = new HttpResponseApi();

        this.ratingBookService.add(lang, dto);
        httpResponseApiMsg.setMessage(this.messageUtilComponent.getMessage("add.success.rating.book", lang));
        httpResponseApiMsg.setHttpStatus(HttpStatus.OK);
        return new ResponseEntity<>(httpResponseApiMsg, HttpStatus.OK);
    }
}
