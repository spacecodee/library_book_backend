package com.spacecodee.library_book_backend.service.user.client;

import com.spacecodee.library_book_backend.model.dto.user.client.PUserClientDto;
import com.spacecodee.library_book_backend.model.dto.user.client.UserClientDto;
import com.spacecodee.library_book_backend.model.vo.user.client.UserClientVo;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IUserClientService {

    List<UserClientDto> getAll();

    Optional<UserClientDto> getById(int id);

    Optional<PUserClientDto> getByUsername(String username);

    boolean existById(int id);

    boolean existByUsername(String username);

    boolean existByPhone(int phone);

    boolean existByEmail(String email);

    @Transactional(rollbackFor = SQLException.class)
    void add(UserClientVo dto);

    @Transactional(rollbackFor = SQLException.class)
    void update(UserClientVo dto);

    void delete(int id);
}