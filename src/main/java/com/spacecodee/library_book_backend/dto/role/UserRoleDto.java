package com.spacecodee.library_book_backend.dto.role;

import lombok.*;

import java.io.Serializable;

/**
 * A DTO for the {@link com.spacecodee.library_book_backend.entity.UserRoleEntity} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class UserRoleDto implements Serializable {
    private int id;
    private String name;
}