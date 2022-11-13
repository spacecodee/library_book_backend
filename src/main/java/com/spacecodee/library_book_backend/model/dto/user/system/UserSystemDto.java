package com.spacecodee.library_book_backend.model.dto.user.system;

import com.spacecodee.library_book_backend.model.dto.role.RoleDto;
import com.spacecodee.library_book_backend.model.dto.user.AUserDto;
import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserSystemDto extends AUserDto {

    private int id;
    private Set<RoleDto> roleDto;
}
