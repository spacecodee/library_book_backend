package com.spacecodee.library_book_backend.service.user.system;

import com.spacecodee.library_book_backend.model.dto.user.system.PUserSystemDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalUserServiceImpl implements UserDetailsService {

    private final UserSystemService userSystemService;

    public PrincipalUserServiceImpl(UserSystemService userSystemService) {
        this.userSystemService = userSystemService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = this.userSystemService
                .getByUsername(username)
                .orElse(new PUserSystemDto());
        return PUserSystemDto.build(user);
    }
}
