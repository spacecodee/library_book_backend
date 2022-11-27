package com.spacecodee.library_book_backend.service.user;

import com.spacecodee.library_book_backend.mappers.user.IGeneralUserMapper;
import com.spacecodee.library_book_backend.model.pojo.UserAccountPojo;
import com.spacecodee.library_book_backend.repository.IUserClientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserGeneralService implements IUserGeneralService {

    private final IUserClientRepository userClientRepository;
    private final IUserClientRepository userSystemRepository;

    public UserGeneralService(IUserClientRepository userClientRepository, IUserClientRepository userSystemRepository) {
        this.userClientRepository = userClientRepository;
        this.userSystemRepository = userSystemRepository;
    }

    @Override
    public Optional<UserAccountPojo> getAccountByUsername(String username) {
        return this.userSystemRepository
                .getByUsername(username)
                .or(() -> this.userClientRepository.getByUsername(username))
                .or(Optional::empty)
                .map(IGeneralUserMapper.INSTANCE::toToPojo);
    }
}
