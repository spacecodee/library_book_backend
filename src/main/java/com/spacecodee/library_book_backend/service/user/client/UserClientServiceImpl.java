package com.spacecodee.library_book_backend.service.user.client;

import com.spacecodee.library_book_backend.component.ExceptionShortComponent;
import com.spacecodee.library_book_backend.dto.role.UserRoleDto;
import com.spacecodee.library_book_backend.dto.user.client.UserClientDto;
import com.spacecodee.library_book_backend.exceptions.NotAddSqlException;
import com.spacecodee.library_book_backend.exceptions.NotUpdateSqlException;
import com.spacecodee.library_book_backend.service.role.RoleServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserClientServiceImpl {

    private final RoleServiceImpl roleService;
    private final UserClientService userClientService;
    private final ExceptionShortComponent exceptionShortComponent;

    private static final String GET_BY_ID_ERROR_USER_CLIENT = "get.by.id.error.user.client";
    private static final Logger LOGGER = LoggerFactory.getLogger(UserClientServiceImpl.class);

    public UserClientServiceImpl(RoleServiceImpl roleService, UserClientService userClientService,
                                 ExceptionShortComponent exceptionShortComponent) {
        this.roleService = roleService;
        this.userClientService = userClientService;
        this.exceptionShortComponent = exceptionShortComponent;
    }

    public List<UserClientDto> getAll() {
        return this.userClientService.getAll();
    }

    public UserClientDto getById(String lang, int id) {
        return this.userClientService
                .getById(id)
                .orElseThrow(() -> this.exceptionShortComponent.notFound(
                        UserClientServiceImpl.GET_BY_ID_ERROR_USER_CLIENT, lang));
    }

    public UserClientDto getByName(String lang, String name) {
        return this.userClientService
                .getByName(name)
                .orElseThrow(() -> this.exceptionShortComponent.notFound("get.by.user.name.error.user.client", lang));
    }

    public void existById(String lang, int id) {
        if (this.userClientService.existById(id)) {
            throw this.exceptionShortComponent.existFound("get.by.id.exists.user.client", lang);
        }
    }

    public void noExistById(String lang, int id) {
        if (!this.userClientService.existById(id)) {
            throw this.exceptionShortComponent.notFound(UserClientServiceImpl.GET_BY_ID_ERROR_USER_CLIENT, lang);
        }
    }

    public void existByUsername(String lang, String name) {
        if (this.userClientService.existByName(name)) {
            throw this.exceptionShortComponent.existFound("get.by.name.exists.user.client", lang);
        }
    }

    public void existByPhone(String lang, int phone) {
        if (this.userClientService.existByPhone(phone)) {
            throw this.exceptionShortComponent.existFound("get.by.phone.exists.people", lang);
        }
    }

    public void existByEmail(String lang, String email) {
        if (this.userClientService.existByEmail(email)) {
            throw this.exceptionShortComponent.existFound("get.by.email.exists.people", lang);
        }
    }

    public void add(String lang, UserClientDto dto) {
        this.setRoleToDto(lang, dto);
        this.existByEmail(lang, dto.getEmail());
        this.existByUsername(lang, dto.getUsername());
        this.existByPhone(lang, dto.getPeopleDto().getPhone());
        try {
            this.userClientService.add(dto);
        } catch (NotAddSqlException e) {
            UserClientServiceImpl.LOGGER.error("error adding: {}", e.getMessage());
            throw this.exceptionShortComponent.notAddSql("add.error.user.client", lang);
        }
    }

    public void update(String lang, UserClientDto dto) {
        this.setRoleToDto(lang, dto);
        this.noExistById(lang, dto.getId());

        List<UserClientDto> users = this.userClientService.getAll();

        try {
            users.forEach(userDto -> {
                if (userDto.getEmail().equalsIgnoreCase(dto.getEmail())) {
                    if (userDto.getId() != dto.getId()) {
                        throw this.exceptionShortComponent.existFound("get.by.email.exists.people", lang);
                    }
                }
                else if (userDto.getUsername().equalsIgnoreCase(dto.getUsername())) {
                    if (userDto.getId() != dto.getId()) {
                        throw this.exceptionShortComponent.existFound("get.by.name.exists.user.client", lang);
                    }
                }
                else if (userDto.getPeopleDto().getPhone() == dto.getPeopleDto().getPhone()) {
                    if (userDto.getId() != dto.getId()) {
                        throw this.exceptionShortComponent.existFound("get.by.phone.exists.people", lang);
                    }
                }
                else {
                    this.userClientService.update(dto);
                }
            });
        } catch (NotUpdateSqlException e) {
            UserClientServiceImpl.LOGGER.error("error updating: {}", e.getMessage());
            throw this.exceptionShortComponent.noUpdateSql("update.error.user.client", lang);
        }
    }

    private void setRoleToDto(String lang, UserClientDto dto) {
        UserRoleDto role = this.roleService.findByName(lang, dto.getUserRolDto().getName());
        dto.setUserRolDto(role);
    }

    public void delete(String lang, int id) {
        this.noExistById(lang, id);
        try {
            this.userClientService.delete(id);
        } catch (NotAddSqlException e) {
            UserClientServiceImpl.LOGGER.error("error deleting: {}", e.getMessage());
            throw this.exceptionShortComponent.noDeleteSql("delete.error.user.client", lang);
        }
    }
}
