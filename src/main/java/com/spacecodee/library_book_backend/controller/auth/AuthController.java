package com.spacecodee.library_book_backend.controller.auth;

import com.spacecodee.library_book_backend.component.MessageUtilComponent;
import com.spacecodee.library_book_backend.model.dto.http.HttpResponseApi;
import com.spacecodee.library_book_backend.model.dto.http.HttpResponseApiMsg;
import com.spacecodee.library_book_backend.model.dto.jwt.JwtDto;
import com.spacecodee.library_book_backend.model.pojo.AuthUserPojo;
import com.spacecodee.library_book_backend.model.vo.user.client.UserClientVo;
import com.spacecodee.library_book_backend.model.vo.user.system.UserSystemVo;
import com.spacecodee.library_book_backend.service.auth.AuthServiceImpl;
import com.spacecodee.library_book_backend.service.user.client.UserClientServiceImpl;
import com.spacecodee.library_book_backend.service.user.system.UserSystemServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
public class AuthController implements IAuthController {

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

    @Override
    public ResponseEntity<HttpResponseApi> registerClient(String lang,
                                                          UserClientVo dto) {
        HttpResponseApi responseApi = new HttpResponseApi();

        this.userClientService.add(lang, dto);
        responseApi.setMessage(this.messageUtilComponent.getMessage("add.success.user.client", lang));
        responseApi.setHttpStatus(HttpStatus.OK);
        return new ResponseEntity<>(responseApi, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpResponseApi> registerUser(String lang, UserSystemVo dto) {
        HttpResponseApi responseApi = new HttpResponseApi();

        this.userSystemService.add(lang, dto);
        responseApi.setMessage(this.messageUtilComponent.getMessage("add.success.user.system", lang));
        responseApi.setHttpStatus(HttpStatus.OK);
        return new ResponseEntity<>(responseApi, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpResponseApiMsg<JwtDto>> login(String lang, AuthUserPojo dto) {
        HttpResponseApiMsg<JwtDto> apiMsg = new HttpResponseApiMsg<>();
        apiMsg.setData(this.authService.login(lang, dto));
        apiMsg.setMessage(this.messageUtilComponent.getMessage("login.success.user", lang));
        apiMsg.setHttpStatus(HttpStatus.OK);
        return new ResponseEntity<>(apiMsg, HttpStatus.OK);
    }
}
