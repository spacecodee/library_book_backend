package com.spacecodee.library_book_backend.entity.book;

import com.spacecodee.library_book_backend.dto.book.flat.BookFlatDto;
import lombok.*;

import java.io.Serializable;

/**
 * A DTO for the {@link BookEntity} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BookAllFlatDto extends BookFlatDto implements Serializable {

    private int id;
}