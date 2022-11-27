package com.spacecodee.library_book_backend.service.user;

import com.spacecodee.library_book_backend.mappers.user.IGeneralUserMapper;
import com.spacecodee.library_book_backend.model.pojo.UserAccountPojo;
import com.spacecodee.library_book_backend.repository.IUserClientRepository;
import com.spacecodee.library_book_backend.repository.IUserSystemRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserGeneralService implements IUserGeneralService {

    private final IUserClientRepository userClientRepository;
    private final IUserSystemRepository userSystemRepository;

    public UserGeneralService(IUserClientRepository userClientRepository, IUserSystemRepository userSystemRepository) {
        this.userClientRepository = userClientRepository;
        this.userSystemRepository = userSystemRepository;
    }

    private Optional<UserAccountPojo> getUserSystemByUsername(String username) {
        return this.userSystemRepository
                .getByUserSystemUsername(username)
                .or(Optional::empty)
                .map(IGeneralUserMapper.INSTANCE::toSystemPojo);
    }

    @Override
    public Optional<UserAccountPojo> getAccountByUsername(String username) {
        final var data = this.getUserSystemByUsername(username);
        if (data.isPresent()) {
            return data;
        }

        return this.userClientRepository
                .getByUsername(username)
                .or(Optional::empty)
                .map(IGeneralUserMapper.INSTANCE::toClientPojo);
    }
}
