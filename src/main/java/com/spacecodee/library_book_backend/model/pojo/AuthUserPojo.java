package com.spacecodee.library_book_backend.model.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class AuthUserPojo implements Serializable {
    @NotEmpty(message = "{is.required.username}")
    private String username;
    @NotEmpty(message = "{is.required.password}")
    private String password;
}
