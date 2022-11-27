package com.spacecodee.library_book_backend.service.user;

import com.spacecodee.library_book_backend.component.ExceptionShortComponent;
import com.spacecodee.library_book_backend.model.pojo.UserAccountPojo;
import org.springframework.stereotype.Service;

@Service
public class UserGeneralServiceImpl {

    private final UserGeneralService userGeneralService;
    private final ExceptionShortComponent exceptionShortComponent;

    public UserGeneralServiceImpl(UserGeneralService userGeneralService,
                                  ExceptionShortComponent exceptionShortComponent) {
        this.userGeneralService = userGeneralService;
        this.exceptionShortComponent = exceptionShortComponent;
    }

    public UserAccountPojo getAccountByUsername(String lang, String username) {
        return this.userGeneralService
                .getAccountByUsername(username)
                .orElseThrow(() -> this.exceptionShortComponent.existFound("get.by.username.error.account", lang));
    }
}
