package com.spacecodee.library_book_backend.controller.generics;

import com.spacecodee.library_book_backend.dto.http.HttpResponseApi;
import com.spacecodee.library_book_backend.dto.http.HttpResponseApiMsg;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("default")
public interface IAllController<L, A, U> {
    @GetMapping()
    ResponseEntity<HttpResponseApiMsg<List<L>>> list(@RequestParam(defaultValue = "en") String lang);

    @GetMapping("/{id}")
    ResponseEntity<HttpResponseApiMsg<L>> getById(@RequestParam(defaultValue = "en") String lang,
                                                  @PathVariable int id);

    @PostMapping()
    ResponseEntity<HttpResponseApi> add(@RequestParam(defaultValue = "en") String lang,
                                        @Valid @RequestBody A dto);

    @PutMapping("/{id}")
    ResponseEntity<HttpResponseApi> update(@RequestParam(defaultValue = "en") String lang,
                                           @PathVariable int id,
                                           @Valid @RequestBody U dto);

    @DeleteMapping("/{id}")
    ResponseEntity<HttpResponseApi> delete(@RequestParam(defaultValue = "en") String lang,
                                           @PathVariable int id);
}
