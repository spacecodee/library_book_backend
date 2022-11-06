package com.spacecodee.library_book_backend.dto.rating.book;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.io.Serializable;

/**
 * A DTO for the {@link com.spacecodee.library_book_backend.entity.rating.UserRatingBookEntity} entity
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RatingBookDto extends UserRatingBookKeyDto implements Serializable {
    @Min(value = 1, message = "{size.rating.book}")
    @Positive(message = "{positive.rating.book}")
    private short ratingBook;
}