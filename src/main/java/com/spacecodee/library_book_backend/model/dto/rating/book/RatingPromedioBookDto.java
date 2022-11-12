package com.spacecodee.library_book_backend.model.dto.rating.book;

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
public class RatingPromedioBookDto extends ARatingBookDto implements Serializable {
    private double promedioRating;
}