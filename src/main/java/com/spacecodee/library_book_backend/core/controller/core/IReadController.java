package com.spacecodee.library_book_backend.core.controller.core;

import com.spacecodee.library_book_backend.dto.http.HttpResponseApiMsg;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("default")
public interface IReadController<R> {

    @ApiImplicitParam(name = "lang", value = "Language", paramType = "query", defaultValue = "en",
            dataTypeClass = String.class)
    @GetMapping()
    ResponseEntity<HttpResponseApiMsg<List<R>>> list(@RequestParam(defaultValue = "en") String lang);

    @ApiImplicitParam(name = "lang", value = "Language", paramType = "query", defaultValue = "en",
            dataTypeClass = String.class)
    @GetMapping("/{id}")
    ResponseEntity<HttpResponseApiMsg<R>> getById(@RequestParam(defaultValue = "en") String lang,
                                                  @PathVariable int id);
}
