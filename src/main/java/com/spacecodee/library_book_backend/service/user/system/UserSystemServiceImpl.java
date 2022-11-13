package com.spacecodee.library_book_backend.service.user.system;

import com.spacecodee.library_book_backend.component.ExceptionShortComponent;
import com.spacecodee.library_book_backend.exceptions.NotAddSqlException;
import com.spacecodee.library_book_backend.exceptions.NotUpdateSqlException;
import com.spacecodee.library_book_backend.model.dto.user.system.UserSystemDto;
import com.spacecodee.library_book_backend.model.vo.user.system.UserSystemVo;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSystemServiceImpl {

    private final UserSystemService userSystemService;
    private final ExceptionShortComponent exceptionShortComponent;
    private final PasswordEncoder passwordEncoder;

    private static final String GET_BY_ID_ERROR_USER_CLIENT = "get.by.id.error.user.system";
    private static final Logger LOGGER = LoggerFactory.getLogger(UserSystemServiceImpl.class);

    public UserSystemServiceImpl(UserSystemService userSystemService,
                                 ExceptionShortComponent exceptionShortComponent, PasswordEncoder passwordEncoder) {
        this.userSystemService = userSystemService;
        this.exceptionShortComponent = exceptionShortComponent;
        this.passwordEncoder = passwordEncoder;
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

    private void noExistById(String lang, int id) {
        if (!this.userSystemService.existById(id)) {
            throw this.exceptionShortComponent.notFound(UserSystemServiceImpl.GET_BY_ID_ERROR_USER_CLIENT, lang);
        }
    }

    private void existByUsername(String lang, String name) {
        if (this.userSystemService.existByUsername(name)) {
            throw this.exceptionShortComponent.existFound("get.by.name.exists.user.system", lang);
        }
    }

    private void existByPhone(String lang, int phone) {
        if (this.userSystemService.existByPhone(phone)) {
            throw this.exceptionShortComponent.existFound("get.by.phone.exists.people", lang);
        }
    }

    private void existByEmail(String lang, String email) {
        if (this.userSystemService.existByEmail(email)) {
            throw this.exceptionShortComponent.existFound("get.by.email.exists.people", lang);
        }
    }

    public void add(String lang, @NotNull UserSystemVo dto) {
        this.existByPhone(lang, dto.getPeopleDto().getPhone());
        this.existByEmail(lang, dto.getEmail());
        this.existByUsername(lang, dto.getUsername());
        try {
            dto.setPassword(this.passwordEncoder.encode(dto.getPassword()));
            this.userSystemService.add(dto);
        } catch (NotAddSqlException e) {
            UserSystemServiceImpl.LOGGER.error("error adding: {}", e.getMessage());
            throw this.exceptionShortComponent.notAddSql("add.error.user.system", lang);
        }
    }

    public void update(String lang, UserSystemVo dto) {
        this.noExistById(lang, dto.getId());

        final var users = this.userSystemService.getAll();

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
