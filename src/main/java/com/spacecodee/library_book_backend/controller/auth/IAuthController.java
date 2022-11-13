package com.spacecodee.library_book_backend.controller.auth;

import com.spacecodee.library_book_backend.model.dto.http.HttpResponseApi;
import com.spacecodee.library_book_backend.model.dto.http.HttpResponseApiMsg;
import com.spacecodee.library_book_backend.model.dto.jwt.JwtDto;
import com.spacecodee.library_book_backend.model.pojo.AuthUserPojo;
import com.spacecodee.library_book_backend.model.vo.user.client.UserClientVo;
import com.spacecodee.library_book_backend.model.vo.user.system.UserSystemVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

public interface IAuthController {

    @ApiImplicitParam(name = "lang", value = "Language", paramType = "query", defaultValue = "en",
            dataTypeClass = String.class)
    @ApiOperation(value = "Register an User Client Endpoint",
            notes = "You can register an user System by this endpoint")
    @PostMapping("/register-client")
    ResponseEntity<HttpResponseApi> registerClient(@RequestParam(defaultValue = "en") String lang,
                                                   @Valid() @RequestBody UserClientVo pojo);

    @ApiImplicitParam(name = "lang", value = "Language", paramType = "query", defaultValue = "en",
            dataTypeClass = String.class)
    @ApiOperation(value = "Register an User System Endpoint",
            notes = "You can register an User Client by this endpoint")
    @PostMapping("/register-user")
    ResponseEntity<HttpResponseApi> registerUser(@RequestParam(defaultValue = "en") String lang,
                                                 @Valid() @RequestBody UserSystemVo dto);

    @ApiImplicitParam(name = "lang", value = "Language", paramType = "query", defaultValue = "en",
            dataTypeClass = String.class)
    @ApiOperation(value = "Login Endpoint", notes = "Here you'll able to login in this app")
    @PostMapping("/login")
    ResponseEntity<HttpResponseApiMsg<JwtDto>> login(@RequestParam(defaultValue = "en") String lang,
                                                     @Valid @RequestBody AuthUserPojo dto);

    @ApiImplicitParam(name = "lang", value = "Language", paramType = "query", defaultValue = "en",
            dataTypeClass = String.class)
    @ApiOperation(value = "Refresh Token Endpoint", notes = "Here you'll able to refresh your token")
    @PostMapping("/refresh-token")
    ResponseEntity<HttpResponseApiMsg<JwtDto>> refresh(@RequestParam(defaultValue = "en") String lang,
                                                       @Valid @RequestBody JwtDto jwtDto);
}
