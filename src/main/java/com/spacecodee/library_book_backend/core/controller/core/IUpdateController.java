package com.spacecodee.library_book_backend.core.controller.core;

import com.spacecodee.library_book_backend.annotations.IsAuthenticatedAsAdminOrUser;
import com.spacecodee.library_book_backend.dto.http.HttpResponseApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("default")
public interface IUpdateController<U> {

    @IsAuthenticatedAsAdminOrUser
    @PutMapping("/{id}")
    ResponseEntity<HttpResponseApi> update(@RequestParam(defaultValue = "en") String lang,
                                           @PathVariable int id,
                                           @Valid @RequestBody U dto);
}
