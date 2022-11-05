package com.spacecodee.library_book_backend.controller.auth;

import com.spacecodee.library_book_backend.component.MessageUtilComponent;
import com.spacecodee.library_book_backend.dto.http.HttpResponseApi;
import com.spacecodee.library_book_backend.dto.http.HttpResponseApiMsg;
import com.spacecodee.library_book_backend.dto.jwt.JwtDto;
import com.spacecodee.library_book_backend.dto.user.UserDto;
import com.spacecodee.library_book_backend.dto.user.client.UserClientDto;
import com.spacecodee.library_book_backend.service.auth.AuthServiceImpl;
import com.spacecodee.library_book_backend.service.user.client.UserClientServiceImpl;
import com.spacecodee.library_book_backend.service.user.system.UserSystemServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
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

    @PostMapping("/register-client")
    public ResponseEntity<HttpResponseApi> registerClient(@RequestParam(defaultValue = "en") String lang,
                                                          @Valid @RequestBody UserClientDto dto) {
        HttpResponseApi responseApi = new HttpResponseApi();

        this.userClientService.add(lang, dto);
        responseApi.setMessage(this.messageUtilComponent.getMessage("add.success.user.client", lang));
        responseApi.setHttpStatus(HttpStatus.OK);
        return new ResponseEntity<>(responseApi, HttpStatus.OK);
    }

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
