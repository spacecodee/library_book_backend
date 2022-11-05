package com.spacecodee.library_book_backend.dto.rating.book;

import com.spacecodee.library_book_backend.dto.book.BookUDto;
import com.spacecodee.library_book_backend.dto.user.client.UserClientDto;
import lombok.*;

import java.io.Serializable;

/**
 * A DTO for the {@link com.spacecodee.library_book_backend.entity.rating.UserRatingBookEntity} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class UserRatingBookDto implements Serializable {
    private short ratingBook;
    private UserClientDto userClientDto;
    private BookUDto bookDto;
}