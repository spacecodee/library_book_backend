package com.spacecodee.library_book_backend.controller.rating.book;

import com.spacecodee.library_book_backend.annotations.IsAuthenticatedAsAdminOrUserOrClient;
import com.spacecodee.library_book_backend.annotations.IsAuthenticatedAsClient;
import com.spacecodee.library_book_backend.exceptions.NotDeleteSqlException;
import com.spacecodee.library_book_backend.model.dto.http.HttpResponseApi;
import com.spacecodee.library_book_backend.model.vo.rating.book.RatingBookVo;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("default")
public interface IRatingBookController {

    @ApiImplicitParam(name = "lang", value = "Language", paramType = "query", defaultValue = "en",
            dataTypeClass = String.class)
    @IsAuthenticatedAsClient
    @PostMapping("/add-rating")
    ResponseEntity<HttpResponseApi> add(@RequestParam(defaultValue = "en") String lang,
                                        @Valid @RequestBody RatingBookVo dto);

    @ApiImplicitParam(name = "lang", value = "Language", paramType = "query", defaultValue = "en",
            dataTypeClass = String.class)
    @IsAuthenticatedAsClient
    @PutMapping("/update-rating/{bookId}")
    ResponseEntity<HttpResponseApi> update(@RequestParam(defaultValue = "en") String lang,
                                           @PathVariable int bookId,
                                           @Valid @RequestBody RatingBookVo dto);

    @ApiImplicitParam(name = "lang", value = "Language", paramType = "query", defaultValue = "en",
            dataTypeClass = String.class)
    @IsAuthenticatedAsAdminOrUserOrClient
    @DeleteMapping("/delete/{bookId}")
    ResponseEntity<HttpResponseApi> delete(@RequestParam(defaultValue = "en") String lang,
                                           @RequestParam int clientId, @PathVariable int bookId)
            throws NotDeleteSqlException;
}
