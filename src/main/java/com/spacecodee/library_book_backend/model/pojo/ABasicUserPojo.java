package com.spacecodee.library_book_backend.model.pojo;

import com.spacecodee.library_book_backend.validations.Validations;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Getter
@Setter
public abstract class ABasicUserPojo {
    @Email(message = "{invalid.email}", regexp = Validations.REGEXP_EMAIL)
    @NotEmpty(message = "{not.empty.user.email}")
    private String email;
}
