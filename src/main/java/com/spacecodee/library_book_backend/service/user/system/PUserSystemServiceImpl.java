package com.spacecodee.library_book_backend.service.user.system;

import com.spacecodee.library_book_backend.dto.user.system.PUserSystemDto;
import com.spacecodee.library_book_backend.dto.user.system.UserSystemDto;
import com.spacecodee.library_book_backend.mappers.user.system.IUserSystemMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PUserSystemServiceImpl implements UserDetailsService {

    private final UserSystemService userSystemService;

    public PUserSystemServiceImpl(UserSystemService userSystemService) {
        this.userSystemService = userSystemService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = this.userSystemService
                .getByName(username)
                .orElse(new UserSystemDto());

        return PUserSystemDto.build(IUserSystemMapper.INSTANCE.dtoToEntity(user));
    }
}
