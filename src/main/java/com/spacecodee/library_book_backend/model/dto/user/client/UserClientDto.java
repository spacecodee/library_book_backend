package com.spacecodee.library_book_backend.model.dto.user.client;

import com.spacecodee.library_book_backend.model.dto.role.RoleDto;
import com.spacecodee.library_book_backend.model.dto.user.AUserDto;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserClientDto extends AUserDto {

    private int id;
    private RoleDto roleDto;
}
