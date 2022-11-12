package com.spacecodee.library_book_backend.controller.auth;

import com.spacecodee.library_book_backend.component.MessageUtilComponent;
import com.spacecodee.library_book_backend.model.dto.http.HttpResponseApi;
import com.spacecodee.library_book_backend.model.dto.http.HttpResponseApiMsg;
import com.spacecodee.library_book_backend.dto.jwt.JwtDto;
import com.spacecodee.library_book_backend.dto.user.UserDto;
import com.spacecodee.library_book_backend.dto.user.client.UserClientADto;
import com.spacecodee.library_book_backend.dto.user.system.UserSystemDto;
import com.spacecodee.library_book_backend.service.auth.AuthServiceImpl;
import com.spacecodee.library_book_backend.service.user.client.UserClientServiceImpl;
import com.spacecodee.library_book_backend.service.user.system.UserSystemServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    private final AuthServiceImpl authService;
    private final UserClientServiceImpl userClientService;
    private final UserSystemServiceImpl userSystemService;
    private final MessageUtilComponent messageUtilComponent;

    public AuthController(AuthServiceImpl authService, UserClientServiceImpl userClientService,
                          UserSystemServiceImpl userSystemService,
                          MessageUtilComponent messageUtilComponent) {
        this.authService = authService;
        this.userClientService = userClientService;
        this.userSystemService = userSystemService;
        this.messageUtilComponent = messageUtilComponent;
    }

    @ApiOperation(value = "Register an User Client Endpoint",
            notes = "You can register an user System by this endpoint")
    @PostMapping("/register-client")
    public ResponseEntity<HttpResponseApi> registerClient(@RequestParam(defaultValue = "en") String lang,
                                                          @Valid() @RequestBody UserClientADto dto) {
        HttpResponseApi responseApi = new HttpResponseApi();

        this.userClientService.add(lang, dto);
        responseApi.setMessage(this.messageUtilComponent.getMessage("add.success.user.client", lang));
        responseApi.setHttpStatus(HttpStatus.OK);
        return new ResponseEntity<>(responseApi, HttpStatus.OK);
    }

    @ApiOperation(value = "Register an User System Endpoint",
            notes = "You can register an User Client by this endpoint")
    @PostMapping("/register-user")
    public ResponseEntity<HttpResponseApi> registerClient(@RequestParam(defaultValue = "en") String lang,
                                                          @Valid() @RequestBody UserSystemDto dto) {
        HttpResponseApi responseApi = new HttpResponseApi();

        this.userSystemService.add(lang, dto);
        responseApi.setMessage(this.messageUtilComponent.getMessage("add.success.user.system", lang));
        responseApi.setHttpStatus(HttpStatus.OK);
        return new ResponseEntity<>(responseApi, HttpStatus.OK);
    }

    @ApiOperation(value = "Login Endpoint", notes = "Here you'll able to login in this app")
    @PostMapping("/login")
    public ResponseEntity<HttpResponseApiMsg<JwtDto>> login(@RequestParam(defaultValue = "en") String lang,
                                                            @Valid @RequestBody UserDto dto) {
        HttpResponseApiMsg<JwtDto> apiMsg = new HttpResponseApiMsg<>();
        apiMsg.setData(this.authService.login(lang, dto));
        apiMsg.setMessage(this.messageUtilComponent.getMessage("login.success.user", lang));
        apiMsg.setHttpStatus(HttpStatus.OK);
        return new ResponseEntity<>(apiMsg, HttpStatus.OK);
    }
}
