package com.spacecodee.library_book_backend.dto.category.book;

import com.spacecodee.library_book_backend.dto.book.BookUDto;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

/**
 * A DTO for the {@link com.spacecodee.library_book_backend.entity.CategoryBookEntity} entity
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CategoryBookLDto extends CategoryBookUDto implements Serializable {

    @NotNull(message = "Category books are required")
    private Set<BookUDto> booksDto;
}