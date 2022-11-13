package com.spacecodee.library_book_backend.controller.user.client;

import com.spacecodee.library_book_backend.annotations.IsAuthenticatedAsAdminOrUser;
import com.spacecodee.library_book_backend.annotations.IsAuthenticatedAsAdminOrUserOrClient;
import com.spacecodee.library_book_backend.exceptions.NotDeleteSqlException;
import com.spacecodee.library_book_backend.model.dto.http.HttpResponseApi;
import com.spacecodee.library_book_backend.model.dto.http.HttpResponseApiMsg;
import com.spacecodee.library_book_backend.model.dto.user.client.UserClientDto;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("default")
public interface IUserClientController {

    @ApiImplicitParam(name = "lang", value = "Language", paramType = "query", defaultValue = "en",
            dataTypeClass = String.class)
    @IsAuthenticatedAsAdminOrUser
    @GetMapping("/get-all")
    ResponseEntity<HttpResponseApiMsg<List<UserClientDto>>> getAll(@RequestParam(defaultValue = "en") String lang);

    @ApiImplicitParam(name = "lang", value = "Language", paramType = "query", defaultValue = "en",
            dataTypeClass = String.class)
    @IsAuthenticatedAsAdminOrUser
    @GetMapping("/get-by/{id}")
    ResponseEntity<HttpResponseApiMsg<UserClientDto>> getById(@RequestParam(defaultValue = "en") String lang,
                                                              @PathVariable int id);

    @ApiImplicitParam(name = "lang", value = "Language", paramType = "query",
            defaultValue = "en", dataTypeClass = String.class)
    @IsAuthenticatedAsAdminOrUserOrClient
    @DeleteMapping("/delete/{id}")
    ResponseEntity<HttpResponseApi> delete(@RequestParam(defaultValue = "en") String lang,
                                           @PathVariable int id) throws NotDeleteSqlException;
}
