package com.spacecodee.library_book_backend.dto.user.client;

import com.spacecodee.library_book_backend.dto.role.UserRoleDto;
import com.spacecodee.library_book_backend.entity.UserClientEntity;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO for the {@link UserClientEntity} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserClientDto extends UserClientUDto implements Serializable {

    @NotNull(message = "{not.null.roles}")
    private UserRoleDto userRolDto;
}