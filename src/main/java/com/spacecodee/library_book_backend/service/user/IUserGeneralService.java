package com.spacecodee.library_book_backend.service.user;

import com.spacecodee.library_book_backend.model.pojo.UserAccountPojo;

import java.util.Optional;

public interface IUserGeneralService {

    Optional<UserAccountPojo> getAccountByUsername(String username);
}
