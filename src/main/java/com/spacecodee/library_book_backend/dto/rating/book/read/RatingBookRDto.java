package com.spacecodee.library_book_backend.dto.rating.book.read;

import com.spacecodee.library_book_backend.dto.book.action.BookUDto;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class RatingBookRDto {

    private short ratingBook;
    private BookUDto bookDto;
}
