package com.spacecodee.library_book_backend.service.user.system;

import com.spacecodee.library_book_backend.entity.UserRoleEntity;
import com.spacecodee.library_book_backend.entity.UserSystemEntity;
import com.spacecodee.library_book_backend.enums.RolNameEnum;
import com.spacecodee.library_book_backend.mappers.user.system.IUserSystemMapper;
import com.spacecodee.library_book_backend.mappers.user.system.IUserSystemReadMapper;
import com.spacecodee.library_book_backend.model.dto.user.system.PUserSystemDto;
import com.spacecodee.library_book_backend.model.dto.user.system.UserSystemDto;
import com.spacecodee.library_book_backend.model.vo.user.system.UserSystemVo;
import com.spacecodee.library_book_backend.repository.IUserRoleRepository;
import com.spacecodee.library_book_backend.repository.IUserSystemRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class UserSystemService implements IUserSystemService {

    private final IUserSystemRepository iUserSystemRepository;
    private final IUserRoleRepository iUserRoleRepository;

    public UserSystemService(IUserSystemRepository iUserSystemRepository, IUserRoleRepository iUserRoleRepository) {
        this.iUserSystemRepository = iUserSystemRepository;
        this.iUserRoleRepository = iUserRoleRepository;
    }

    @Override
    public List<UserSystemDto> getAll() {
        return IUserSystemReadMapper.INSTANCE.mapClients(iUserSystemRepository.findAll());
    }

    @Override
    public Optional<UserSystemDto> getById(int id) {
        return this.iUserSystemRepository
                .findById(id).or(Optional::empty)
                .map(IUserSystemReadMapper.INSTANCE::toDto);
    }

    @Override
    public Optional<PUserSystemDto> getByUsername(String username) {
        return this.iUserSystemRepository
                .findByUserSystemUsername(username).or(Optional::empty)
                .map(IUserSystemReadMapper.INSTANCE::entityToPDto);
    }

    @Override
    public boolean existById(int id) {
        return this.iUserSystemRepository.existsById(id);
    }

    @Override
    public boolean existByUsername(String name) {
        return this.iUserSystemRepository.existsByUserSystemUsername(name);
    }

    public boolean existByPhone(int phone) {
        return this.iUserSystemRepository.existsByPeopleEntityPeoplePhone(phone);
    }

    public boolean existByEmail(String email) {
        return this.iUserSystemRepository.existsByUserSystemEmail(email);
    }

    @Override
    public void add(UserSystemVo dto) {
        this.iUserSystemRepository.save(this.mapUser(dto));
    }

    @Override
    public void update(UserSystemVo dto) {
        this.iUserSystemRepository.save(this.mapUser(dto));
    }

    @Override
    public void delete(int id) {
        this.iUserSystemRepository.deleteById(id);
    }

    private UserSystemEntity mapUser(@NotNull UserSystemVo vo) {
        var roles = new HashSet<UserRoleEntity>();
        if (vo.getRoleName().contains("admin")) {
            var role = this.iUserRoleRepository.findByUserRoleName(RolNameEnum.ROLE_ADMIN).orElseThrow();
            roles.add(role);
        }
        var role = this.iUserRoleRepository.findByUserRoleName(RolNameEnum.ROLE_USER).orElseThrow();
        roles.add(role);
        var userClient = IUserSystemMapper.INSTANCE.toEntity(vo);
        IUserSystemMapper.INSTANCE.updateSystemRoles(userClient, roles);
        return userClient;
    }
}
