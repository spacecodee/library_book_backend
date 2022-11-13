package com.spacecodee.library_book_backend.controller.user.system;

import com.spacecodee.library_book_backend.annotations.IsAuthenticatedAsAdminOrUser;
import com.spacecodee.library_book_backend.annotations.IsAuthenticatedAsAdminOrUserOrClient;
import com.spacecodee.library_book_backend.exceptions.NotDeleteSqlException;
import com.spacecodee.library_book_backend.model.dto.http.HttpResponseApi;
import com.spacecodee.library_book_backend.model.dto.http.HttpResponseApiMsg;
import com.spacecodee.library_book_backend.model.dto.user.system.UserSystemDto;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("default")
public interface IUserSystemController {

    @ApiImplicitParam(name = "lang", value = "Language", paramType = "query", defaultValue = "en",
            dataTypeClass = String.class)
    @IsAuthenticatedAsAdminOrUser
    @GetMapping("/get-all")
    ResponseEntity<HttpResponseApiMsg<List<UserSystemDto>>> getAll(@RequestParam(defaultValue = "en") String lang);

    @ApiImplicitParam(name = "lang", value = "Language", paramType = "query", defaultValue = "en",
            dataTypeClass = String.class)
    @IsAuthenticatedAsAdminOrUser
    @GetMapping("/get-by/{id}")
    ResponseEntity<HttpResponseApiMsg<UserSystemDto>> getById(@RequestParam(defaultValue = "en") String lang,
                                                              @PathVariable int id);

    @ApiImplicitParam(name = "lang", value = "Language", paramType = "query",
            defaultValue = "en", dataTypeClass = String.class)
    @IsAuthenticatedAsAdminOrUserOrClient
    @DeleteMapping("/delete/{id}")
    ResponseEntity<HttpResponseApi> delete(@RequestParam(defaultValue = "en") String lang,
                                           @PathVariable int id) throws NotDeleteSqlException;
}
