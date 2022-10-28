package com.spacecodee.library_book_backend.dto.book;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO for the {@link com.spacecodee.library_book_backend.entity.BookEntity} entity
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BookUDto extends BookADto implements Serializable {

    @NotNull(message = "Book id is required")
    private int id;
}