package com.spacecodee.library_book_backend.dto.user.system;

import com.spacecodee.library_book_backend.dto.people.PeopleDto;
import com.spacecodee.library_book_backend.dto.role.UserRoleDto;
import com.spacecodee.library_book_backend.entity.UserSystemEntity;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    @NotEmpty(message = "Username is required")
    private String email;
    @NotEmpty(message = "Username is required")
    private String username;
    @NotEmpty(message = "Password is required")
    private String password;
    @NotNull(message = "People is required")
    private PeopleDto peopleDto;
    @NotNull(message = "Roles is required")
    private Set<UserRoleDto> userRolesDto = new HashSet<>();
}