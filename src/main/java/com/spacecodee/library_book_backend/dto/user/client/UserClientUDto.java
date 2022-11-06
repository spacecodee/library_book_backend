package com.spacecodee.library_book_backend.dto.user.client;

import com.spacecodee.library_book_backend.entity.UserClientEntity;
import lombok.*;

import java.io.Serializable;

/**
 * A DTO for the {@link UserClientEntity} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserClientUDto extends UserClientADto implements Serializable {

    private int id;
}