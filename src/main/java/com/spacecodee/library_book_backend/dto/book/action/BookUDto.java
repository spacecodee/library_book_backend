package com.spacecodee.library_book_backend.dto.book.action;

import com.spacecodee.library_book_backend.dto.book.action.BookFlatDto;
import com.spacecodee.library_book_backend.entity.book.BookEntity;
import lombok.*;

import java.io.Serializable;

/**
 * A DTO for the {@link BookEntity} entity
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BookUDto extends BookFlatDto implements Serializable {

    private int id;
}