package com.spacecodee.library_book_backend.annotations;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasRole(T(com.spacecodee.library_book_backend.enums.RolNameEnum).ROLE_STUDENT)")
public @interface IsAuthenticatedAsClient {
}
