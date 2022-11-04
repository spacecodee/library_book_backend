package com.spacecodee.library_book_backend.service.user.system;

import com.spacecodee.library_book_backend.component.ExceptionShortComponent;
import com.spacecodee.library_book_backend.dto.role.UserRoleDto;
import com.spacecodee.library_book_backend.dto.user.system.UserSystemDto;
import com.spacecodee.library_book_backend.exceptions.NotAddSqlException;
import com.spacecodee.library_book_backend.exceptions.NotUpdateSqlException;
import com.spacecodee.library_book_backend.service.role.RoleServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class UserSystemServiceImpl {

    private final RoleServiceImpl roleService;
    private final UserSystemService userSystemService;
    private final ExceptionShortComponent exceptionShortComponent;

    private static final String GET_BY_ID_ERROR_USER_CLIENT = "get.by.id.error.user.system";
    private static final Logger LOGGER = LoggerFactory.getLogger(UserSystemServiceImpl.class);

    public UserSystemServiceImpl(RoleServiceImpl roleService, UserSystemService userSystemService,
                                 ExceptionShortComponent exceptionShortComponent) {
        this.roleService = roleService;
        this.userSystemService = userSystemService;
        this.exceptionShortComponent = exceptionShortComponent;
    }

    public List<UserSystemDto> getAll() {
        return this.userSystemService.getAll();
    }

    public UserSystemDto getById(String lang, int id) {
        return this.userSystemService
                .getById(id)
                .orElseThrow(() -> this.exceptionShortComponent.notFound(
                        UserSystemServiceImpl.GET_BY_ID_ERROR_USER_CLIENT, lang));
    }

    public UserSystemDto getByName(String lang, String name) {
        return this.userSystemService
                .getByName(name)
                .orElseThrow(() -> this.exceptionShortComponent.notFound("get.by.user.name.error.user.system", lang));
    }

    public void existById(String lang, int id) {
        if (this.userSystemService.existById(id)) {
            throw this.exceptionShortComponent.existFound("get.by.id.exists.user.system", lang);
        }
    }

    public void noExistById(String lang, int id) {
        if (!this.userSystemService.existById(id)) {
            throw this.exceptionShortComponent.notFound(UserSystemServiceImpl.GET_BY_ID_ERROR_USER_CLIENT, lang);
        }
    }

    public void existByUsername(String lang, String name) {
        if (this.userSystemService.existByName(name)) {
            throw this.exceptionShortComponent.existFound("get.by.name.exists.user.system", lang);
        }
    }

    public void existByPhone(String lang, int phone) {
        if (this.userSystemService.existByPhone(phone)) {
            throw this.exceptionShortComponent.existFound("get.by.phone.exists.people", lang);
        }
    }

    public void existByEmail(String lang, String email) {
        if (this.userSystemService.existByEmail(email)) {
            throw this.exceptionShortComponent.existFound("get.by.email.exists.people", lang);
        }
    }

    public void add(String lang, UserSystemDto dto) {
        this.setRolesToDto(lang, dto);
        this.existByEmail(lang, dto.getEmail());
        this.existByUsername(lang, dto.getUsername());
        this.existByPhone(lang, dto.getPeopleDto().getPhone());
        try {
            this.userSystemService.add(dto);
        } catch (NotAddSqlException e) {
            UserSystemServiceImpl.LOGGER.error("error adding: {}", e.getMessage());
            throw this.exceptionShortComponent.notAddSql("add.error.user.system", lang);
        }
    }

    public void update(String lang, UserSystemDto dto) {
        this.setRolesToDto(lang, dto);
        this.noExistById(lang, dto.getId());

        List<UserSystemDto> users = this.userSystemService.getAll();

        try {
            users.forEach(userDto -> {
                if (userDto.getEmail().equalsIgnoreCase(dto.getEmail())) {
                    if (userDto.getId() != dto.getId()) {
                        throw this.exceptionShortComponent.existFound("get.by.email.exists.people", lang);
                    }
                }
                else if (userDto.getUsername().equalsIgnoreCase(dto.getUsername())) {
                    if (userDto.getId() != dto.getId()) {
                        throw this.exceptionShortComponent.existFound("get.by.name.exists.user.system", lang);
                    }
                }
                else if (userDto.getPeopleDto().getPhone() == dto.getPeopleDto().getPhone()) {
                    if (userDto.getId() != dto.getId()) {
                        throw this.exceptionShortComponent.existFound("get.by.phone.exists.people", lang);
                    }
                }
                else {
                    this.userSystemService.update(dto);
                }
            });
        } catch (NotUpdateSqlException e) {
            UserSystemServiceImpl.LOGGER.error("error updating: {}", e.getMessage());
            throw this.exceptionShortComponent.noUpdateSql("update.error.user.system", lang);
        }
    }

    private void setRolesToDto(String lang, UserSystemDto dto) {
        final AtomicBoolean containsAdmin = new AtomicBoolean(false);
        final Set<UserRoleDto> roles = new HashSet<>();
        dto.getUserRolesDto().forEach(userRoleDto -> {
            if (userRoleDto.getName().contains("admin")) {
                containsAdmin.set(true);
            }
        });

        if (containsAdmin.get()) {
            UserRoleDto roleAdmin = this.roleService.findByName(lang, "admin");
            roles.add(roleAdmin);
        }
        UserRoleDto roleUser = this.roleService.findByName(lang, "user");
        roles.add(roleUser);

        dto.setUserRolesDto(roles);
    }

    public void delete(String lang, int id) {
        this.noExistById(lang, id);
        try {
            this.userSystemService.delete(id);
        } catch (NotAddSqlException e) {
            UserSystemServiceImpl.LOGGER.error("error deleting: {}", e.getMessage());
            throw this.exceptionShortComponent.noDeleteSql("delete.error.user.system", lang);
        }
    }
}
