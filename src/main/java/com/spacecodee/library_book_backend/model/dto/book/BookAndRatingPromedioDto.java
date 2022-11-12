package com.spacecodee.library_book_backend.model.dto.book;

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
public class BookAndRatingPromedioDto extends ABasicBookDto implements Serializable {
    private int id;
    private RatingPromedioBookDto ratingPromedioBookDto;
}