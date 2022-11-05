package com.spacecodee.library_book_backend.controller.user.client;

import com.spacecodee.library_book_backend.component.MessageUtilComponent;
import com.spacecodee.library_book_backend.controller.generics.IAllController;
import com.spacecodee.library_book_backend.dto.http.HttpResponseApi;
import com.spacecodee.library_book_backend.dto.http.HttpResponseApiMsg;
import com.spacecodee.library_book_backend.dto.user.client.UserClientDto;
import com.spacecodee.library_book_backend.service.user.client.UserClientServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/user-client")
public class UserClientController implements IAllController<UserClientDto, UserClientDto, UserClientDto> {


    private final UserClientServiceImpl userClientService;
    private final MessageUtilComponent messageUtilComponent;

    public UserClientController(UserClientServiceImpl userClientService,
                                MessageUtilComponent messageUtilComponent) {
        this.userClientService = userClientService;
        this.messageUtilComponent = messageUtilComponent;
    }

    @Override
    public ResponseEntity<HttpResponseApiMsg<List<UserClientDto>>> list(String lang) {
        final HttpResponseApiMsg<List<UserClientDto>> httpResponseApiMsg = new HttpResponseApiMsg<>();
        httpResponseApiMsg.setData(this.userClientService.getAll());

        if (httpResponseApiMsg.getData().isEmpty()) {
            httpResponseApiMsg.setMessage(
                    this.messageUtilComponent.getMessage("get.all.no.content.user.client", lang));
            httpResponseApiMsg.setHttpStatus(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(httpResponseApiMsg, HttpStatus.OK);
        }

        httpResponseApiMsg.setMessage(this.messageUtilComponent.getMessage("get.all.success.user.client", lang));
        httpResponseApiMsg.setHttpStatus(HttpStatus.OK);
        return new ResponseEntity<>(httpResponseApiMsg, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpResponseApiMsg<UserClientDto>> getById(String lang, int id) {
        final HttpResponseApiMsg<UserClientDto> httpResponseApiMsg = new HttpResponseApiMsg<>();
        httpResponseApiMsg.setData(this.userClientService.getById(lang, id));
        httpResponseApiMsg.setMessage(this.messageUtilComponent.getMessage("get.by.id.success.rating.book", lang));
        httpResponseApiMsg.setHttpStatus(HttpStatus.OK);
        return new ResponseEntity<>(httpResponseApiMsg, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpResponseApi> add(String lang, UserClientDto dto) {
        HttpResponseApi httpResponseApiMsg = new HttpResponseApi();

        this.userClientService.add(lang, dto);
        httpResponseApiMsg.setMessage(this.messageUtilComponent.getMessage("add.success.user.client", lang));
        httpResponseApiMsg.setHttpStatus(HttpStatus.OK);
        return new ResponseEntity<>(httpResponseApiMsg, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpResponseApi> update(String lang, int id, UserClientDto dto) {
        HttpResponseApi httpResponseApiMsg = new HttpResponseApi();

        dto.setId(id);
        this.userClientService.update(lang, dto);
        httpResponseApiMsg.setMessage(this.messageUtilComponent.getMessage("update.success.user.client", lang));
        httpResponseApiMsg.setHttpStatus(HttpStatus.OK);

        return new ResponseEntity<>(httpResponseApiMsg, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpResponseApi> delete(String lang, int id) {
        HttpResponseApi httpResponseApiMsg = new HttpResponseApi();

        this.userClientService.delete(lang, id);
        httpResponseApiMsg.setMessage(this.messageUtilComponent.getMessage("delete.success.user.client", lang));
        httpResponseApiMsg.setHttpStatus(HttpStatus.OK);

        return new ResponseEntity<>(httpResponseApiMsg, HttpStatus.OK);
    }
}
