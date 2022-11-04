package com.spacecodee.library_book_backend.controller.role;

import com.spacecodee.library_book_backend.component.MessageUtilComponent;
import com.spacecodee.library_book_backend.dto.http.HttpResponseApiMsg;
import com.spacecodee.library_book_backend.dto.role.UserRoleDto;
import com.spacecodee.library_book_backend.service.role.RoleServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user-role")
public class RoleController {

    private final RoleServiceImpl roleService;
    private final MessageUtilComponent messageUtilComponent;

    public RoleController(RoleServiceImpl roleService, MessageUtilComponent messageUtilComponent) {
        this.roleService = roleService;
        this.messageUtilComponent = messageUtilComponent;
    }

    @GetMapping("/{name}")
    public ResponseEntity<HttpResponseApiMsg<UserRoleDto>> getByName(@RequestParam(defaultValue = "en") String lang,
                                                                     @PathVariable() String name) {
        final HttpResponseApiMsg<UserRoleDto> httpResponseApiMsg = new HttpResponseApiMsg<>();
        httpResponseApiMsg.setData(this.roleService.findByName(lang, name));
        httpResponseApiMsg.setMessage(this.messageUtilComponent.getMessage("get.by.name.success.role", lang));
        httpResponseApiMsg.setHttpStatus(HttpStatus.OK);
        return new ResponseEntity<>(httpResponseApiMsg, HttpStatus.OK);
    }
}
