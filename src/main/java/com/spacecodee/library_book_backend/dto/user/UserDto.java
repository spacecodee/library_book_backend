package com.spacecodee.library_book_backend.dto.user;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class UserDto {

    @NotEmpty(message = "{is.required.username}")
    private String username;
    @NotEmpty(message = "{is.required.password}")
    private String password;
}
