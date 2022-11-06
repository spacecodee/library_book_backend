package com.spacecodee.library_book_backend.dto.rating.book;

import lombok.*;

import javax.validation.constraints.Positive;
import java.io.Serializable;

/**
 * A DTO for the {@link com.spacecodee.library_book_backend.entity.rating.UserRatingBookKeyEntity} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
@ToString()
public class UserRatingBookKeyDto implements Serializable {
    @Positive(message = "{positive.user.id}")
    private int clientId;
    @Positive(message = "{positive.book.id}")
    private int bookId;
}