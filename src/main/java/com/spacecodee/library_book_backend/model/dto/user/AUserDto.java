package com.spacecodee.library_book_backend.model.dto.user;

import com.spacecodee.library_book_backend.model.dto.people.PeopleDto;
import com.spacecodee.library_book_backend.model.pojo.ABasicUserPojo;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class AUserDto extends ABasicUserPojo {
    @NotEmpty(message = "{is.required.username}")
    private String username;
    @NotEmpty(message = "{is.required.password}")
    private String password;
    @NotNull(message = "{not.null.people}")
    private PeopleDto peopleDto;
}
