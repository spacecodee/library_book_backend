package com.spacecodee.library_book_backend.core.controller.core;

import com.spacecodee.library_book_backend.annotations.IsAuthenticatedAsAdminOrUser;
import com.spacecodee.library_book_backend.model.dto.http.HttpResponseApi;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@RequestMapping("default")
public interface ICreateController<C> {

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true,
                    paramType = "header", dataTypeClass = String.class),
            @ApiImplicitParam(name = "lang", value = "Language", paramType = "query",
                    defaultValue = "en", dataTypeClass = String.class),
    })
    @IsAuthenticatedAsAdminOrUser
    @PostMapping()
    ResponseEntity<HttpResponseApi> add(@RequestParam(defaultValue = "en") String lang,
                                        @Valid @RequestBody C dto);
}
