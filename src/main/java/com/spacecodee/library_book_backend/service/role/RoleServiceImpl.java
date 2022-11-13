package com.spacecodee.library_book_backend.service.role;

import com.spacecodee.library_book_backend.component.ExceptionShortComponent;
import com.spacecodee.library_book_backend.model.dto.role.RoleDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl {

    private final RoleService roleService;
    private final ExceptionShortComponent exceptionShortComponent;

    public RoleServiceImpl(RoleService roleService, ExceptionShortComponent exceptionShortComponent) {
        this.roleService = roleService;
        this.exceptionShortComponent = exceptionShortComponent;
    }

    public List<RoleDto> getAll() {
        return this.roleService.getAll();
    }

    public RoleDto findByName(String lang, String name) {
        return this.roleService
                .findByName(name)
                .orElseThrow(() -> this.exceptionShortComponent.notFound("get.by.name.error.role", lang));
    }
}
