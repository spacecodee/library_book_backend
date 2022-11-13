package com.spacecodee.library_book_backend.model.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class AuthUserPojo {
    @NotEmpty(message = "{is.required.username}")
    private String username;
    @NotEmpty(message = "{is.required.password}")
    private String password;
}
