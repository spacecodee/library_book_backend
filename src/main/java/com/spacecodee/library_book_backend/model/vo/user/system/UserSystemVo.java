package com.spacecodee.library_book_backend.model.vo.user.system;

import com.spacecodee.library_book_backend.model.dto.user.AUserDto;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserSystemVo extends AUserDto {

    private int id;
    private String roleName;
}
