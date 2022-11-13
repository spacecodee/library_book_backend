package com.spacecodee.library_book_backend.model.vo.user.client;

import com.spacecodee.library_book_backend.model.dto.user.AUserDto;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserClientVo extends AUserDto {

    private int id;
}
