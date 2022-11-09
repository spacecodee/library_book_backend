package com.spacecodee.library_book_backend.controller.user.system;

import com.spacecodee.library_book_backend.annotations.IsAuthenticatedAsAdminOrUser;
import com.spacecodee.library_book_backend.component.MessageUtilComponent;
import com.spacecodee.library_book_backend.core.controller.IRDController;
import com.spacecodee.library_book_backend.dto.http.HttpResponseApi;
import com.spacecodee.library_book_backend.dto.http.HttpResponseApiMsg;
import com.spacecodee.library_book_backend.dto.user.system.UserSystemDto;
import com.spacecodee.library_book_backend.service.user.system.UserSystemServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/user-system")
public class UserSystemController implements IRDController<UserSystemDto, Integer> {

    private final UserSystemServiceImpl userSystemService;
    private final MessageUtilComponent messageUtilComponent;

    public UserSystemController(UserSystemServiceImpl userSystemService,
                                MessageUtilComponent messageUtilComponent) {
        this.userSystemService = userSystemService;
        this.messageUtilComponent = messageUtilComponent;
    }

    @IsAuthenticatedAsAdminOrUser
    @Override
    public ResponseEntity<HttpResponseApiMsg<List<UserSystemDto>>> list(String lang) {
        final HttpResponseApiMsg<List<UserSystemDto>> httpResponseApiMsg = new HttpResponseApiMsg<>();
        httpResponseApiMsg.setData(this.userSystemService.getAll());

        if (httpResponseApiMsg.getData().isEmpty()) {
            httpResponseApiMsg.setMessage(
                    this.messageUtilComponent.getMessage("get.all.no.content.user.system", lang));
            httpResponseApiMsg.setHttpStatus(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(httpResponseApiMsg, HttpStatus.OK);
        }

        httpResponseApiMsg.setMessage(this.messageUtilComponent.getMessage("get.all.success.user.system", lang));
        httpResponseApiMsg.setHttpStatus(HttpStatus.OK);
        return new ResponseEntity<>(httpResponseApiMsg, HttpStatus.OK);
    }

    @IsAuthenticatedAsAdminOrUser
    @Override
    public ResponseEntity<HttpResponseApiMsg<UserSystemDto>> getById(String lang, int id) {
        final HttpResponseApiMsg<UserSystemDto> httpResponseApiMsg = new HttpResponseApiMsg<>();
        httpResponseApiMsg.setData(this.userSystemService.getById(lang, id));
        httpResponseApiMsg.setMessage(this.messageUtilComponent.getMessage("get.by.id.success.user.system", lang));
        httpResponseApiMsg.setHttpStatus(HttpStatus.OK);
        return new ResponseEntity<>(httpResponseApiMsg, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpResponseApi> delete(String lang, Integer id) {
        HttpResponseApi httpResponseApiMsg = new HttpResponseApi();

        this.userSystemService.delete(lang, id);
        httpResponseApiMsg.setMessage(this.messageUtilComponent.getMessage("delete.success.user.system", lang));
        httpResponseApiMsg.setHttpStatus(HttpStatus.OK);

        return new ResponseEntity<>(httpResponseApiMsg, HttpStatus.OK);
    }
}
