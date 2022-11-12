package com.spacecodee.library_book_backend.dto.book.action;

import com.spacecodee.library_book_backend.entity.book.BookEntity;
import lombok.*;

import javax.validation.constraints.Positive;
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
public class BookFlatADto extends BookFlatDto implements Serializable {

    @Positive(message = "{positive.category.book.id}")
    private int categoryId;
}