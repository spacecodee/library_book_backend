package com.spacecodee.library_book_backend.dto.book.action;

import com.spacecodee.library_book_backend.dto.category.book.CategoryBookUDto;
import com.spacecodee.library_book_backend.entity.book.BookEntity;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO for the {@link BookEntity} entity
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