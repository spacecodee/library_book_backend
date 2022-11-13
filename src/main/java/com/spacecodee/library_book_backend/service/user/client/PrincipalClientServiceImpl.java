package com.spacecodee.library_book_backend.service.user.client;

import com.spacecodee.library_book_backend.model.dto.user.client.PUserClientDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalClientServiceImpl implements UserDetailsService {

    private final UserClientService userClientService;

    public PrincipalClientServiceImpl(UserClientService userClientService) {
        this.userClientService = userClientService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = this.userClientService.getByUsername(username).orElse(new PUserClientDto());
        return PUserClientDto.build(user);
    }
}
