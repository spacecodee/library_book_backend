package com.spacecodee.library_book_backend.service.role;

import com.spacecodee.library_book_backend.dto.role.UserRoleDto;
import com.spacecodee.library_book_backend.mappers.role.IUserRoleMapper;
import com.spacecodee.library_book_backend.repository.IUserRoleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public List<UserRoleDto> getAll() {
        List<UserRoleDto> roles = new ArrayList<>();
        this.userRoleRepository.findAll().forEach(userRoleEntity -> roles.add(
                IUserRoleMapper.INSTANCE.entityToDtos(userRoleEntity)));

        return roles;
    }
}
