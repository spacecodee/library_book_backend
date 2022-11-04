package com.spacecodee.library_book_backend.service.user.client;

import com.spacecodee.library_book_backend.dto.user.client.UserClientDto;
import com.spacecodee.library_book_backend.mappers.user.client.IUserClientMapper;
import com.spacecodee.library_book_backend.repository.IUserClientRepository;
import com.spacecodee.library_book_backend.service.IGenericService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserClientService implements IGenericService<UserClientDto, UserClientDto, UserClientDto> {

    private final IUserClientRepository iUserClientRepository;

    public UserClientService(IUserClientRepository iUserClientRepository) {
        this.iUserClientRepository = iUserClientRepository;
    }

    @Override
    public List<UserClientDto> getAll() {
        final List<UserClientDto> list = new ArrayList<>();
        this.iUserClientRepository.findAll().forEach(entity -> list.add(
                IUserClientMapper.INSTANCE.entityToDto(entity)));

        return list;
    }

    @Override
    public Optional<UserClientDto> getById(int id) {
        return this.iUserClientRepository
                .findById(id).or(Optional::empty)
                .map(IUserClientMapper.INSTANCE::entityToDto);
    }

    @Override
    public Optional<UserClientDto> getByName(String name) {
        return this.iUserClientRepository
                .findByUsername(name).or(Optional::empty)
                .map(IUserClientMapper.INSTANCE::entityToDto);
    }

    @Override
    public boolean existById(int id) {
        return this.iUserClientRepository.existsById(id);
    }

    @Override
    public boolean existByName(String name) {
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
    public void add(UserClientDto dto) {
        this.iUserClientRepository.save(IUserClientMapper.INSTANCE.dtoToEntity(dto));
    }

    @Transactional(rollbackFor = SQLException.class)
    @Override
    public void update(UserClientDto dto) {
        this.iUserClientRepository.save(IUserClientMapper.INSTANCE.dtoToEntity(dto));
    }

    @Override
    public void delete(int id) {
        this.iUserClientRepository.deleteById(id);
    }
}
