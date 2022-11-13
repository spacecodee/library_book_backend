package com.spacecodee.library_book_backend.controller.category.book;

import com.spacecodee.library_book_backend.annotations.IsAuthenticatedAsAdmin;
import com.spacecodee.library_book_backend.annotations.IsAuthenticatedAsAdminOrUser;
import com.spacecodee.library_book_backend.exceptions.NotAddSqlException;
import com.spacecodee.library_book_backend.exceptions.NotDeleteSqlException;
import com.spacecodee.library_book_backend.exceptions.NotUpdateSqlException;
import com.spacecodee.library_book_backend.model.dto.category.book.CategoryBookAndBookDto;
import com.spacecodee.library_book_backend.model.dto.category.book.CategoryBookDto;
import com.spacecodee.library_book_backend.model.dto.http.HttpResponseApi;
import com.spacecodee.library_book_backend.model.dto.http.HttpResponseApiMsg;
import com.spacecodee.library_book_backend.model.vo.category.book.CategoryBookVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("default")
public interface ICategoryBookController {

    //dto for user into system
    @ApiImplicitParam(name = "lang", value = "Language", paramType = "query", defaultValue = "en",
            dataTypeClass = String.class)
    @IsAuthenticatedAsAdmin
    @GetMapping("/get-all")
    ResponseEntity<HttpResponseApiMsg<List<CategoryBookDto>>> getAll(@RequestParam(defaultValue = "en") String lang);

    @ApiImplicitParam(name = "lang", value = "Language", paramType = "query", defaultValue = "en",
            dataTypeClass = String.class)
    @IsAuthenticatedAsAdmin
    @GetMapping("/get-by/{id}")
    ResponseEntity<HttpResponseApiMsg<CategoryBookDto>> getByCategoryId(@RequestParam(defaultValue = "en") String lang,
                                                                        @PathVariable int id);

    @ApiImplicitParam(name = "lang", value = "Language", paramType = "query", defaultValue = "en",
            dataTypeClass = String.class)
    @GetMapping("/find-all")
    ResponseEntity<HttpResponseApiMsg<List<CategoryBookAndBookDto>>> findAll(
            @RequestParam(defaultValue = "en") String lang);

    @ApiImplicitParam(name = "lang", value = "Language", paramType = "query", defaultValue = "en",
            dataTypeClass = String.class)
    @GetMapping("/find-by/{id}")
    ResponseEntity<HttpResponseApiMsg<CategoryBookAndBookDto>> getByIdCategoryBook(
            @RequestParam(defaultValue = "en") String lang,
            @PathVariable int id);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "lang", value = "Language", paramType = "query",
                    defaultValue = "en", dataTypeClass = String.class),
    })
    @IsAuthenticatedAsAdminOrUser
    @PostMapping("/add")
    ResponseEntity<HttpResponseApi> add(@RequestParam(defaultValue = "en") String lang,
                                        @Valid @RequestBody CategoryBookVo dto) throws NotAddSqlException;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "lang", value = "Language", paramType = "query",
                    defaultValue = "en", dataTypeClass = String.class),
    })
    @IsAuthenticatedAsAdminOrUser
    @PutMapping("/update/{id}")
    ResponseEntity<HttpResponseApi> update(@RequestParam(defaultValue = "en") String lang,
                                           @PathVariable int id, @Valid @RequestBody CategoryBookVo dto)
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
