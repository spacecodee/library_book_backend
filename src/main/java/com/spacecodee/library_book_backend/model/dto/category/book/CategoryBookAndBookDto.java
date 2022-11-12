package com.spacecodee.library_book_backend.model.dto.category.book;

import com.spacecodee.library_book_backend.model.dto.book.BookAndRatingPromedioDto;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

/**
 * A DTO for the {@link com.spacecodee.library_book_backend.entity.category.book.CategoryBookEntity} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CategoryBookAndBookDto extends CategoryBookDto implements Serializable {

    private Set<BookAndRatingPromedioDto> bookDto;
}