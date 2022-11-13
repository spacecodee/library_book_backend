package com.spacecodee.library_book_backend.controller.user.system;

import com.spacecodee.library_book_backend.component.MessageUtilComponent;
import com.spacecodee.library_book_backend.exceptions.NotDeleteSqlException;
import com.spacecodee.library_book_backend.model.dto.http.HttpResponseApi;
import com.spacecodee.library_book_backend.model.dto.http.HttpResponseApiMsg;
import com.spacecodee.library_book_backend.model.dto.user.system.UserSystemDto;
import com.spacecodee.library_book_backend.service.user.system.UserSystemServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/user-system")
public class UserSystemController implements IUserSystemController {

    private final UserSystemServiceImpl userSystemService;
    private final MessageUtilComponent messageUtilComponent;

    public UserSystemController(UserSystemServiceImpl userSystemService,
                                MessageUtilComponent messageUtilComponent) {
        this.userSystemService = userSystemService;
        this.messageUtilComponent = messageUtilComponent;
    }

    @Override
    public ResponseEntity<HttpResponseApiMsg<List<UserSystemDto>>> getAll(String lang) {
        final var response = new HttpResponseApiMsg<List<UserSystemDto>>();
        response.setData(this.userSystemService.getAll());
        if (response.getData().isEmpty()) {
            response.setMessage(
                    this.messageUtilComponent.getMessage("get.all.no.content.user.system", lang));
            response.setHttpStatus(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        response.setMessage(this.messageUtilComponent.getMessage("get.all.success.user.system", lang));
        response.setHttpStatus(HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpResponseApiMsg<UserSystemDto>> getById(String lang, int id) {
        final HttpResponseApiMsg<UserSystemDto> httpResponseApiMsg = new HttpResponseApiMsg<>();
        httpResponseApiMsg.setData(this.userSystemService.getById(lang, id));
        httpResponseApiMsg.setMessage(this.messageUtilComponent.getMessage("get.by.id.success.user.system", lang));
        httpResponseApiMsg.setHttpStatus(HttpStatus.OK);
        return new ResponseEntity<>(httpResponseApiMsg, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpResponseApi> delete(String lang, int id) throws NotDeleteSqlException {
        final var response = new HttpResponseApi();

        this.userSystemService.delete(lang, id);
        response.setMessage(this.messageUtilComponent.getMessage("delete.success.user.system", lang));
        response.setHttpStatus(HttpStatus.OK);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
