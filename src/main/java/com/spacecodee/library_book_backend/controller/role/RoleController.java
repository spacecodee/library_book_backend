package com.spacecodee.library_book_backend.controller.role;

import com.spacecodee.library_book_backend.component.MessageUtilComponent;
import com.spacecodee.library_book_backend.model.dto.http.HttpResponseApiMsg;
import com.spacecodee.library_book_backend.model.dto.role.RoleDto;
import com.spacecodee.library_book_backend.service.role.RoleServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/user-role")
public class RoleController implements IRoleController {

    private final RoleServiceImpl roleService;
    private final MessageUtilComponent messageUtilComponent;

    public RoleController(RoleServiceImpl roleService, MessageUtilComponent messageUtilComponent) {
        this.roleService = roleService;
        this.messageUtilComponent = messageUtilComponent;
    }

    @Override
    public ResponseEntity<HttpResponseApiMsg<List<RoleDto>>> getAll(String lang) {
        final HttpResponseApiMsg<List<RoleDto>> httpResponseApiMsg = new HttpResponseApiMsg<>();

        httpResponseApiMsg.setData(this.roleService.getAll());
        httpResponseApiMsg.setMessage(this.messageUtilComponent.getMessage("get.all.roles", lang));
        httpResponseApiMsg.setHttpStatus(HttpStatus.OK);

        return new ResponseEntity<>(httpResponseApiMsg, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpResponseApiMsg<RoleDto>> getByName(String lang, String name) {
        final HttpResponseApiMsg<RoleDto> httpResponseApiMsg = new HttpResponseApiMsg<>();
        httpResponseApiMsg.setData(this.roleService.findByName(lang, name));
        httpResponseApiMsg.setMessage(this.messageUtilComponent.getMessage("get.by.name.success.role", lang));
        httpResponseApiMsg.setHttpStatus(HttpStatus.OK);
        return new ResponseEntity<>(httpResponseApiMsg, HttpStatus.OK);
    }
}
