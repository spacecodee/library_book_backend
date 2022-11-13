package com.spacecodee.library_book_backend.controller.role;

import com.spacecodee.library_book_backend.annotations.IsAuthenticatedAsAdmin;
import com.spacecodee.library_book_backend.annotations.IsAuthenticatedAsAdminOrUser;
import com.spacecodee.library_book_backend.model.dto.http.HttpResponseApiMsg;
import com.spacecodee.library_book_backend.model.dto.role.RoleDto;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("default")
public interface IRoleController {

    @ApiImplicitParam(name = "lang", value = "Language", paramType = "query", defaultValue = "en",
            dataTypeClass = String.class)
    @IsAuthenticatedAsAdminOrUser
    @GetMapping("/get-all")
    ResponseEntity<HttpResponseApiMsg<List<RoleDto>>> getAll(@RequestParam(defaultValue = "en") String lang);

    @ApiImplicitParam(name = "lang", value = "Language", paramType = "query", defaultValue = "en",
            dataTypeClass = String.class)
    @IsAuthenticatedAsAdmin
    @GetMapping("/get-by/{name}")
    ResponseEntity<HttpResponseApiMsg<RoleDto>> getByName(@RequestParam(defaultValue = "en") String lang,
                                                          @PathVariable() String name);
}
