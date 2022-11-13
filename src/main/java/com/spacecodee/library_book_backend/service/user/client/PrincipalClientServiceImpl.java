package com.spacecodee.library_book_backend.service.user.client;

import com.spacecodee.library_book_backend.component.ExceptionShortComponent;
import com.spacecodee.library_book_backend.model.dto.user.client.PUserClientDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalClientServiceImpl implements UserDetailsService {

    private final UserClientService userClientService;
    private final ExceptionShortComponent exceptionShortComponent;

    public PrincipalClientServiceImpl(UserClientService userClientService,
                                      ExceptionShortComponent exceptionShortComponent) {
        this.userClientService = userClientService;
        this.exceptionShortComponent = exceptionShortComponent;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = this.userClientService
                .getByUsername(username)
                .orElseThrow(() -> this.exceptionShortComponent
                        .notFound("get.by.user.name.error.user.system", "eng"));
        return PUserClientDto.build(user);
    }
}
