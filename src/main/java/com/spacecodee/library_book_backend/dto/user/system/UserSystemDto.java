package com.spacecodee.library_book_backend.dto.user.system;

import com.spacecodee.library_book_backend.dto.people.PeopleDto;
import com.spacecodee.library_book_backend.dto.role.UserRoleDto;
import com.spacecodee.library_book_backend.entity.UserSystemEntity;
import com.spacecodee.library_book_backend.validations.Validations;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DTO for the {@link UserSystemEntity} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class UserSystemDto implements Serializable {
    private int id;
    @Email(message = "{invalid.email}", regexp = Validations.REGEXP_EMAIL)
    @NotEmpty(message = "{not.empty.user.email}")
    private String email;
    @Size(min = 4, message = "{min.length.4}")
    @NotEmpty(message = "{not.empty.user.username}")
    private String username;
    @NotEmpty(message = "{not.empty.user.password}")
    private String password;
    @NotNull(message = "{not.null.people}")
    private PeopleDto peopleDto;
    @NotNull(message = "{not.null.roles}")
    private Set<UserRoleDto> userRolesDto = new HashSet<>();
}