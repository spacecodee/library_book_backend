package com.spacecodee.library_book_backend.dto.user.client;

import com.spacecodee.library_book_backend.dto.people.PeopleDto;
import com.spacecodee.library_book_backend.dto.role.UserRoleDto;
import com.spacecodee.library_book_backend.entity.UserClientEntity;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO for the {@link UserClientEntity} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class UserClientDto implements Serializable {
    private int id;
    @NotEmpty(message = "Username is required")
    private String email;
    @NotEmpty(message = "Username is required")
    private String username;
    @NotEmpty(message = "Password is required")
    private String password;
    @NotNull(message = "Role is required")
    private UserRoleDto userRolDto;
    @NotNull(message = "People is required")
    private PeopleDto peopleDto;
}