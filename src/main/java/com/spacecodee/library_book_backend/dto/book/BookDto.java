package com.spacecodee.library_book_backend.dto.book;

import com.spacecodee.library_book_backend.dto.category.book.CategoryBookUDto;
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
public class BookDto extends BookUDto implements Serializable {

    @NotNull(message = "{not.null.category.book}")
    private CategoryBookUDto categoryBookDto;
}