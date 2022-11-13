package com.spacecodee.library_book_backend.service.user.system;

import com.spacecodee.library_book_backend.model.dto.user.system.UserSystemDto;
import com.spacecodee.library_book_backend.model.vo.user.system.UserSystemVo;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IUserSystemService {

    List<UserSystemDto> getAll();

    Optional<UserSystemDto> getById(int id);

    Optional<UserSystemDto> getByUsername(String username);

    boolean existById(int id);

    boolean existByUsername(String username);

    boolean existByPhone(int phone);

    boolean existByEmail(String email);

    @Transactional(rollbackFor = SQLException.class)
    void add(UserSystemVo dto);

    @Transactional(rollbackFor = SQLException.class)
    void update(UserSystemVo dto);

    void delete(int id);
}