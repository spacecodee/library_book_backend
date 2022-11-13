package com.spacecodee.library_book_backend.service.user.system;

import com.spacecodee.library_book_backend.component.ExceptionShortComponent;
import com.spacecodee.library_book_backend.model.dto.user.system.PUserSystemDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalUserServiceImpl implements UserDetailsService {

    private final UserSystemService userSystemService;
    private final ExceptionShortComponent exceptionShortComponent;

    public PrincipalUserServiceImpl(UserSystemService userSystemService,
                                    ExceptionShortComponent exceptionShortComponent) {
        this.userSystemService = userSystemService;
        this.exceptionShortComponent = exceptionShortComponent;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = this.userSystemService
                .getByUsername(username)
                .orElseThrow(() -> this.exceptionShortComponent.notFound("get.by.user.name.error.user.system", "eng"));
        return PUserSystemDto.build(user);
    }
}
