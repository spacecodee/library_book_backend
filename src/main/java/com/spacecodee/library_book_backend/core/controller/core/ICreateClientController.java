package com.spacecodee.library_book_backend.core.controller.core;

import com.spacecodee.library_book_backend.annotations.IsAuthenticatedAsClient;
import com.spacecodee.library_book_backend.model.dto.http.HttpResponseApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@RequestMapping("default")
public interface ICreateClientController<C> {

    @IsAuthenticatedAsClient
    @PostMapping()
    ResponseEntity<HttpResponseApi> add(@RequestParam(defaultValue = "en") String lang,
                                        @Valid @RequestBody C dto);
}
