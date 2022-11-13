package com.spacecodee.library_book_backend.service.role;

import com.spacecodee.library_book_backend.mappers.role.IUserRoleMapper;
import com.spacecodee.library_book_backend.model.dto.role.RoleDto;
import com.spacecodee.library_book_backend.repository.IUserRoleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService implements IRoleService {

    private final IUserRoleRepository userRoleRepository;

    public RoleService(IUserRoleRepository userRoleRepository) {this.userRoleRepository = userRoleRepository;}

    @Override
    public List<RoleDto> getAll() {
        List<RoleDto> roles = new ArrayList<>();
        this.userRoleRepository.findAll().forEach(userRoleEntity -> roles.add(
                IUserRoleMapper.INSTANCE.entityToDtos(userRoleEntity)));

        return roles;
    }

    @Override
    public Optional<RoleDto> findByName(String name) {
        return this.userRoleRepository
                .findByUserRoleName(IUserRoleMapper.INSTANCE.getRole(name))
                .or(Optional::empty)
                .map(IUserRoleMapper.INSTANCE::entityToDtos);
    }
}
