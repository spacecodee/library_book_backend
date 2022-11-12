package com.spacecodee.library_book_backend.dto.category.book;

import com.spacecodee.library_book_backend.dto.book.action.BookUDto;
import com.spacecodee.library_book_backend.entity.category.book.CategoryBookEntity;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

/**
 * A DTO for the {@link CategoryBookEntity} entity
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CategoryBookLDto extends CategoryBookUDto implements Serializable {
    private Set<BookUDto> booksDto;
}