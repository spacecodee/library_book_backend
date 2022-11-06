package com.spacecodee.library_book_backend.dto.rating.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A DTO for the {@link com.spacecodee.library_book_backend.entity.rating.UserRatingBookEntity} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingBookDto implements Serializable {
    @Size(min = 1, max = 5, message = "{size.rating.book}")
    @Positive(message = "{positive.rating.book}")
    private short ratingBook;
    @Positive(message = "{positive.user.id}")
    private int userId;
    @Positive(message = "{positive.book.id}")
    private int bookId;
}