package com.spacecodee.library_book_backend.service.auth;

import com.spacecodee.library_book_backend.component.ExceptionShortComponent;
import com.spacecodee.library_book_backend.exceptions.LoginAuthException;
import com.spacecodee.library_book_backend.jwt.JwtProvider;
import com.spacecodee.library_book_backend.model.dto.jwt.JwtDto;
import com.spacecodee.library_book_backend.model.pojo.AuthUserPojo;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class AuthServiceImpl {

    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;
    private final ExceptionShortComponent exceptionShortComponent;

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);

    public AuthServiceImpl(
            JwtProvider jwtProvider, AuthenticationManager authenticationManager,
            ExceptionShortComponent exceptionShortComponent) {
        this.jwtProvider = jwtProvider;
        this.authenticationManager = authenticationManager;
        this.exceptionShortComponent = exceptionShortComponent;
    }

    public JwtDto login(String lang, AuthUserPojo dto) {
        String jwt;
        try {
            Authentication authentication = this.getAuthentication(dto);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            jwt = this.jwtProvider.generateToken(authentication);

            return new JwtDto(jwt);
        } catch (LoginAuthException exception) {
            AuthServiceImpl.LOGGER.error("error login: {}", exception.getMessage());
            throw this.exceptionShortComponent.loginAuth("login.error.user", lang);
        }
    }

    public JwtDto refreshToken(String lang, JwtDto token) {
        String jwt;
        try {
            jwt = this.jwtProvider.refreshToken(token);
            return new JwtDto(jwt);
        } catch (ParseException e) {
            AuthServiceImpl.LOGGER.error("error refresh token: {}", e.getMessage());
            throw this.exceptionShortComponent.loginAuth("refresh.token.error", lang);
        }
    }

    private Authentication getAuthentication(@NotNull AuthUserPojo dto) {
        return this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getUsername(),
                        dto.getPassword()
                )
        );
    }
}
