package com.spacecodee.library_book_backend.dto.user.client;

import com.spacecodee.library_book_backend.dto.people.PeopleDto;
import com.spacecodee.library_book_backend.entity.UserClientEntity;
import com.spacecodee.library_book_backend.validations.Validations;
import lombok.*;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link UserClientEntity} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class UserClientADto implements Serializable {

    @Email(message = "{invalid.email}", regexp = Validations.REGEXP_EMAIL)
    @NotEmpty(message = "{not.empty.email}")
    private String email;
    @Size(min = 4, message = "{min.length.4}")
    @NotEmpty(message = "{not.empty.username}")
    private String username;
    @NotEmpty(message = "{not.empty.password}")
    private String password;
    @NotNull(message = "{not.null.people}")
    private PeopleDto peopleDto;
}