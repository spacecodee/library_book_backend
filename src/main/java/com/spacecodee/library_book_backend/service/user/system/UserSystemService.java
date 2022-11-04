package com.spacecodee.library_book_backend.service.user.system;

import com.spacecodee.library_book_backend.dto.user.system.UserSystemDto;
import com.spacecodee.library_book_backend.exceptions.NotAddSqlException;
import com.spacecodee.library_book_backend.mappers.user.system.IUserSystemMapper;
import com.spacecodee.library_book_backend.repository.IUserSystemRepository;
import com.spacecodee.library_book_backend.service.generics.IFirstService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserSystemService implements IFirstService<UserSystemDto> {

    private final IUserSystemRepository iUserSystemRepository;

    public UserSystemService(IUserSystemRepository iUserSystemRepository) {
        this.iUserSystemRepository = iUserSystemRepository;
    }

    @Transactional(readOnly = true, rollbackFor = SQLException.class)
    @Override
    public List<UserSystemDto> getAll() {
        final List<UserSystemDto> list = new ArrayList<>();
        this.iUserSystemRepository.findAll().forEach(entity -> list.add(
                IUserSystemMapper.INSTANCE.entityToDto(entity)));

        return list;
    }

    @Transactional(readOnly = true, rollbackFor = SQLException.class)
    @Override
    public Optional<UserSystemDto> getById(int id) {
        return this.iUserSystemRepository
                .findById(id).or(Optional::empty)
                .map(IUserSystemMapper.INSTANCE::entityToDto);
    }

    @Transactional(readOnly = true, rollbackFor = SQLException.class)
    @Override
    public Optional<UserSystemDto> getByName(String name) {
        return this.iUserSystemRepository
                .findByUserSystemUsername(name).or(Optional::empty)
                .map(IUserSystemMapper.INSTANCE::entityToDto);
    }

    @Override
    public boolean existById(int id) {
        return this.iUserSystemRepository.existsById(id);
    }

    @Override
    public boolean existByName(String name) {
        return this.iUserSystemRepository.existsByUserSystemUsername(name);
    }

    public boolean existByPhone(int phone) {
        return this.iUserSystemRepository.existsByPeopleEntityPeoplePhone(phone);
    }

    public boolean existByEmail(String email) {
        return this.iUserSystemRepository.existsByUserSystemEmail(email);
    }

    @Transactional(rollbackFor = NotAddSqlException.class)
    @Override
    public void add(UserSystemDto dto) {
        this.iUserSystemRepository.save(IUserSystemMapper.INSTANCE.dtoToEntity(dto));
    }

    @Transactional(rollbackFor = SQLException.class)
    @Override
    public void update(UserSystemDto dto) {
        this.iUserSystemRepository.save(IUserSystemMapper.INSTANCE.dtoToEntity(dto));
    }

    @Override
    public void delete(int id) {
        this.iUserSystemRepository.deleteById(id);
    }
}
