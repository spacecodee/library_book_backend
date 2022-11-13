package com.spacecodee.library_book_backend.controller.user.client;

import com.spacecodee.library_book_backend.component.MessageUtilComponent;
import com.spacecodee.library_book_backend.exceptions.NotDeleteSqlException;
import com.spacecodee.library_book_backend.model.dto.http.HttpResponseApi;
import com.spacecodee.library_book_backend.model.dto.http.HttpResponseApiMsg;
import com.spacecodee.library_book_backend.model.dto.user.client.UserClientDto;
import com.spacecodee.library_book_backend.service.user.client.UserClientServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/user-client")
public class UserClientController implements IUserClientController {


    private final UserClientServiceImpl userClientService;
    private final MessageUtilComponent messageUtilComponent;

    public UserClientController(UserClientServiceImpl userClientService,
                                MessageUtilComponent messageUtilComponent) {
        this.userClientService = userClientService;
        this.messageUtilComponent = messageUtilComponent;
    }

    @Override
    public ResponseEntity<HttpResponseApiMsg<List<UserClientDto>>> getAll(String lang) {
        final var response = new HttpResponseApiMsg<List<UserClientDto>>();
        response.setData(this.userClientService.getAll());
        if (response.getData().isEmpty()) {
            response.setMessage(
                    this.messageUtilComponent.getMessage("get.all.no.content.user.client", lang));
            response.setHttpStatus(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        response.setMessage(this.messageUtilComponent.getMessage("get.all.success.user.client", lang));
        response.setHttpStatus(HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpResponseApiMsg<UserClientDto>> getById(String lang, int id) {
        final HttpResponseApiMsg<UserClientDto> httpResponseApiMsg = new HttpResponseApiMsg<>();
        httpResponseApiMsg.setData(this.userClientService.getById(lang, id));
        httpResponseApiMsg.setMessage(this.messageUtilComponent.getMessage("get.by.id.success.user.client", lang));
        httpResponseApiMsg.setHttpStatus(HttpStatus.OK);
        return new ResponseEntity<>(httpResponseApiMsg, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpResponseApi> delete(String lang, int id) throws NotDeleteSqlException {
        final var response = new HttpResponseApi();

        this.userClientService.delete(lang, id);
        response.setMessage(this.messageUtilComponent.getMessage("delete.success.user.client", lang));
        response.setHttpStatus(HttpStatus.OK);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
