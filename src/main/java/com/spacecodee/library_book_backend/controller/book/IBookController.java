package com.spacecodee.library_book_backend.controller.book;

import com.spacecodee.library_book_backend.annotations.IsAuthenticatedAsAdmin;
import com.spacecodee.library_book_backend.annotations.IsAuthenticatedAsAdminOrUser;
import com.spacecodee.library_book_backend.exceptions.NotAddSqlException;
import com.spacecodee.library_book_backend.exceptions.NotDeleteSqlException;
import com.spacecodee.library_book_backend.exceptions.NotUpdateSqlException;
import com.spacecodee.library_book_backend.model.dto.book.BookAndCategoryDto;
import com.spacecodee.library_book_backend.model.dto.book.ShowBookDto;
import com.spacecodee.library_book_backend.model.dto.http.HttpResponseApi;
import com.spacecodee.library_book_backend.model.dto.http.HttpResponseApiMsg;
import com.spacecodee.library_book_backend.model.vo.book.BookVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("default")
public interface IBookController {

    //dto for user into system
    @ApiImplicitParam(name = "lang", value = "Language", paramType = "query", defaultValue = "en",
            dataTypeClass = String.class)
    @GetMapping("/get-all")
    ResponseEntity<HttpResponseApiMsg<List<BookAndCategoryDto>>> findAll(
            @RequestParam(defaultValue = "en") String lang);

    @ApiImplicitParam(name = "lang", value = "Language", paramType = "query", defaultValue = "en",
            dataTypeClass = String.class)
    @GetMapping("/get-by/{id}")
    ResponseEntity<HttpResponseApiMsg<BookAndCategoryDto>> getById(@RequestParam(defaultValue = "en") String lang,
                                                                   @PathVariable int id);

    @ApiImplicitParam(name = "lang", value = "Language", paramType = "query", defaultValue = "en",
            dataTypeClass = String.class)
    @GetMapping("/find-by/{bookId}")
    ResponseEntity<HttpResponseApiMsg<ShowBookDto>> getByBookAndClientId(
            @RequestParam(defaultValue = "en") String lang,
            @PathVariable int bookId, @RequestParam String username);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "lang", value = "Language", paramType = "query",
                    defaultValue = "en", dataTypeClass = String.class),
    })
    @IsAuthenticatedAsAdminOrUser
    @PostMapping("/add")
    ResponseEntity<HttpResponseApi> add(@RequestParam(defaultValue = "en") String lang,
                                        @Valid @RequestBody BookVo dto) throws NotAddSqlException;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "lang", value = "Language", paramType = "query",
                    defaultValue = "en", dataTypeClass = String.class),
    })
    @IsAuthenticatedAsAdminOrUser
    @PutMapping("/update/{id}")
    ResponseEntity<HttpResponseApi> update(@RequestParam(defaultValue = "en") String lang,
                                           @PathVariable int id, @Valid @RequestBody BookVo dto)
            throws NotUpdateSqlException;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "lang", value = "Language", paramType = "query",
                    defaultValue = "en", dataTypeClass = String.class),
    })
    @IsAuthenticatedAsAdmin
    @DeleteMapping("/delete/{id}")
    ResponseEntity<HttpResponseApi> delete(@RequestParam(defaultValue = "en") String lang,
                                           @PathVariable int id) throws NotDeleteSqlException;
}
