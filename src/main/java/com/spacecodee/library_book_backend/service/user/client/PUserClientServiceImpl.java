package com.spacecodee.library_book_backend.service.user.client;

import com.spacecodee.library_book_backend.component.ExceptionShortComponent;
import com.spacecodee.library_book_backend.dto.user.client.PUserClientDto;
import com.spacecodee.library_book_backend.mappers.user.client.IUserClientMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PUserClientServiceImpl implements UserDetailsService {

    private final UserClientService userClientService;
    private final ExceptionShortComponent exceptionShortComponent;

    public PUserClientServiceImpl(UserClientService userClientService,
                                  ExceptionShortComponent exceptionShortComponent) {
        this.userClientService = userClientService;
        this.exceptionShortComponent = exceptionShortComponent;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = this.userClientService
                .getByName(username)
                .orElseThrow(() -> this.exceptionShortComponent.notFound("find.by.username.error", "en"));

        return PUserClientDto.build(IUserClientMapper.INSTANCE.dtoToEntity(user));
    }
}
