package com.spacecodee.library_book_backend.model.dto.book;

import com.spacecodee.library_book_backend.model.dto.category.book.CategoryBookDto;
import com.spacecodee.library_book_backend.model.dto.rating.book.RatingPromedioBookDto;
import lombok.*;

import java.io.Serializable;

/**
 * A DTO for the {@link com.spacecodee.library_book_backend.entity.book.BookEntity} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BookAndRatingDto extends BookDto implements Serializable {
    private CategoryBookDto categoryBookDto;
    private RatingPromedioBookDto ratingPromedioBookDto;
}