package com.spacecodee.library_book_backend.service.role;

import com.spacecodee.library_book_backend.model.dto.role.RoleDto;

import java.util.List;
import java.util.Optional;

public interface IRoleService {

    List<RoleDto> getAll();

    Optional<RoleDto> findByName(String name);
}
