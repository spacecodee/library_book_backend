package com.spacecodee.library_book_backend.service.role;

import com.spacecodee.library_book_backend.dto.role.UserRoleDto;
import com.spacecodee.library_book_backend.mappers.role.IUserRoleMapper;
import com.spacecodee.library_book_backend.repository.IUserRoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    private final IUserRoleRepository userRoleRepository;

    public RoleService(IUserRoleRepository userRoleRepository) {this.userRoleRepository = userRoleRepository;}

    public Optional<UserRoleDto> findByName(String name) {
        return this.userRoleRepository
                .findByUserRoleName(IUserRoleMapper.INSTANCE.getRole(name))
                .or(Optional::empty)
                .map(IUserRoleMapper.INSTANCE::entityToDtos);
    }
}
