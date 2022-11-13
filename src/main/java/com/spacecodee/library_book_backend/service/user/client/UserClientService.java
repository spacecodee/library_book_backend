package com.spacecodee.library_book_backend.service.user.client;

import com.spacecodee.library_book_backend.entity.UserClientEntity;
import com.spacecodee.library_book_backend.enums.RolNameEnum;
import com.spacecodee.library_book_backend.mappers.user.client.IUserClientMapper;
import com.spacecodee.library_book_backend.mappers.user.client.IUserClientReadMapper;
import com.spacecodee.library_book_backend.model.dto.user.client.PUserClientDto;
import com.spacecodee.library_book_backend.model.dto.user.client.UserClientDto;
import com.spacecodee.library_book_backend.model.vo.user.client.UserClientVo;
import com.spacecodee.library_book_backend.repository.IUserClientRepository;
import com.spacecodee.library_book_backend.repository.IUserRoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class UserClientService implements IUserClientService {

    private final IUserClientRepository iUserClientRepository;
    private final IUserRoleRepository iUserRoleRepository;

    public UserClientService(IUserClientRepository iUserClientRepository, IUserRoleRepository iUserRoleRepository) {
        this.iUserClientRepository = iUserClientRepository;
        this.iUserRoleRepository = iUserRoleRepository;
    }

    @Override
    public List<UserClientDto> getAll() {
        return IUserClientReadMapper.INSTANCE.mapClients(iUserClientRepository.findAll());
    }

    @Override
    public Optional<UserClientDto> getById(int id) {
        return this.iUserClientRepository
                .findById(id).or(Optional::empty)
                .map(IUserClientReadMapper.INSTANCE::toDto);
    }

    @Override
    public Optional<PUserClientDto> getByUsername(String username) {
        return this.iUserClientRepository
                .findByUsername(username).or(Optional::empty)
                .map(IUserClientReadMapper.INSTANCE::entityToPDto);
    }

    @Override
    public boolean existById(int id) {
        return this.iUserClientRepository.existsById(id);
    }

    @Override
    public boolean existByUsername(String name) {
        return this.iUserClientRepository.existsByUsername(name);
    }

    public boolean existByPhone(int phone) {
        return this.iUserClientRepository.existsByPeopleEntityPeoplePhone(phone);
    }

    public boolean existByEmail(String email) {
        return this.iUserClientRepository.existsByUserEmail(email);
    }

    @Transactional(rollbackFor = SQLException.class)
    @Override
    public void add(UserClientVo dto) {
        this.iUserClientRepository.save(this.mapClient(dto));
    }

    @Transactional(rollbackFor = SQLException.class)
    @Override
    public void update(UserClientVo dto) {
        this.iUserClientRepository.save(this.mapClient(dto));
    }

    @Transactional(rollbackFor = SQLException.class)
    @Override
    public void delete(int id) {
        this.iUserClientRepository.deleteById(id);
    }

    private UserClientEntity mapClient(UserClientVo dto) {
        var role = this.iUserRoleRepository.findByUserRoleName(RolNameEnum.ROLE_STUDENT).orElseThrow();
        var userClient = IUserClientMapper.INSTANCE.toEntity(dto);
        IUserClientMapper.INSTANCE.updateClientRoles(userClient, role);
        return userClient;
    }
}
